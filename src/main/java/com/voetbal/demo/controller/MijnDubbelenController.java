package com.voetbal.demo.controller;

import com.voetbal.demo.model.Gebruiker;
import com.voetbal.demo.model.VoetbalPlaatje;
import com.voetbal.demo.service.GebruikerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.*;

@SessionAttributes({"gebruiker", "uitnodigingen", "dubbelenenvrienden"})
@Controller
public class MijnDubbelenController {
    @Autowired
    GebruikerService gebruikerService;

    @Autowired
    MijnPlaatjesController mijnPlaatjesController;

    @GetMapping("mijnDubbelen")
    public String mijnDubbelenHandler(Model model) {
        Gebruiker gebruiker = (Gebruiker) model.getAttribute("gebruiker");
        gebruiker = gebruikerService.getGebruikerByGebruikersnaam(gebruiker.getGebruikersnaam());
        List<VoetbalPlaatje> dubbelen = gebruiker.getDubbelen();
        List<Gebruiker> vrienden = gebruiker.getVrienden();
        Map<VoetbalPlaatje, String> dubbelenVriend = new HashMap<>();

        voegVriendToeAanPlaatje(dubbelen, vrienden, dubbelenVriend);
        Map<VoetbalPlaatje, String> treeMap = new TreeMap<VoetbalPlaatje, String>(dubbelenVriend);

        model.addAttribute("gebruiker", gebruiker);
        model.addAttribute("dubbelenenvrienden", treeMap);
        return "mijn_dubbelen";
    }

    private void voegVriendToeAanPlaatje(List<VoetbalPlaatje> dubbelen, List<Gebruiker> vrienden, Map<VoetbalPlaatje, String> voetbalPlaatjeVriend) {
        for (VoetbalPlaatje dubbel : dubbelen) {
            String vriendenZonderDubbel = "";
            for (Gebruiker vriend : vrienden) {
                if(vriend.getOntbrekenden().contains(dubbel)){
                    if(vriendenZonderDubbel.length()>0){
                        vriendenZonderDubbel = vriendenZonderDubbel + ", " + vriend.getGebruikersnaam();
                    } else {
                        vriendenZonderDubbel = vriend.getGebruikersnaam();
                    }
                }
            }
            voetbalPlaatjeVriend.put(dubbel,vriendenZonderDubbel);
        }
    }


}
