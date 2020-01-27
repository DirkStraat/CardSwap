package com.voetbal.demo.controller;

import com.voetbal.demo.model.Gebruiker;
import com.voetbal.demo.model.VoetbalPlaatje;
import com.voetbal.demo.service.GebruikerService;
import com.voetbal.demo.service.VoetbalPlaatjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SessionAttributes({"gebruiker", "voetbalPlaatjes"})
@Controller
public class AllePlaatjesController {
    @Autowired
    VoetbalPlaatjeService voetbalPlaatjeService;

    @Autowired
    GebruikerService gebruikerService;

    @Autowired
    InvoerDubbelenController invoerDubbelenController;

    public void allePlaatjesHandler(Model model){
        List<VoetbalPlaatje> voetbalPlaatjes = voetbalPlaatjeService.getVoetbalPlaatjes();
        model.addAttribute("voetbalPlaatjes", voetbalPlaatjes);
    }

    @PostMapping("mijnPlaatjes")
    public String mijnPlaatjesHandler(@RequestParam(name = "ids") String ids, Model model){
        List<VoetbalPlaatje> voetbalPlaatjes = new ArrayList<>();
        if(ids.length()>0) {
            voetbalPlaatjes = voetbalPlaatjeService.getVoetbalPlaatsByCSString(ids);
        }

        Gebruiker gebruiker = (Gebruiker)model.getAttribute("gebruiker");
        gebruiker = gebruikerService.getGebruikerByGebruikersnaam(gebruiker.getGebruikersnaam());
        gebruiker.setOntbrekenden(voetbalPlaatjeService.getVoetbalPlaatjes());

        for (VoetbalPlaatje plaatje : voetbalPlaatjes) {
            gebruiker.getOntbrekenden().remove(plaatje);
        }

        gebruikerService.saveGebruiker(gebruiker);

        invoerDubbelenController.invoerDubbelenHandler(model);
        return "invoer_dubbelen";
    }

}
