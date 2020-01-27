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

@SessionAttributes({"gebruiker", "uitnodigingen", "voetbalplaatjesenvrienden"})
@Controller
public class MijnPlaatjesController {
    @Autowired
    GebruikerService gebruikerService;

    @GetMapping("mijnPlaatjes")
    public String mijnPlaatjesHandler(Model model){
        Gebruiker gebruiker = (Gebruiker) model.getAttribute("gebruiker");
        gebruiker = gebruikerService.getGebruikerByGebruikersnaam(gebruiker.getGebruikersnaam());
        List<VoetbalPlaatje> ontbrekenden = gebruiker.getOntbrekenden();
        List<Gebruiker> vrienden = gebruiker.getVrienden();
        Map<VoetbalPlaatje, String> voetbalPlaatjeVriend = new HashMap<>();

        voegVriendToeAanPlaatje(ontbrekenden, vrienden, voetbalPlaatjeVriend);
        Map<VoetbalPlaatje, String> treeMap = new TreeMap<VoetbalPlaatje, String>(voetbalPlaatjeVriend);


        model.addAttribute("voetbalplaatjesenvrienden" , treeMap);
        return "mijn_plaatjes";
    }

    private void voegVriendToeAanPlaatje(List<VoetbalPlaatje> ontbrekenden, List<Gebruiker> vrienden, Map<VoetbalPlaatje, String> voetbalPlaatjeVriend) {
        for (VoetbalPlaatje ontbrekend : ontbrekenden) {
            String vriendenMetPlaatje = "";
            for (Gebruiker vriend : vrienden) {
                if(vriend.getDubbelen().contains(ontbrekend)){
                    if(vriendenMetPlaatje.length()>0){
                        vriendenMetPlaatje = vriendenMetPlaatje + ", " + vriend.getGebruikersnaam();
                    } else {
                        vriendenMetPlaatje = vriend.getGebruikersnaam();
                    }
                }
            }
            voetbalPlaatjeVriend.put(ontbrekend,vriendenMetPlaatje);
        }
    }

}
