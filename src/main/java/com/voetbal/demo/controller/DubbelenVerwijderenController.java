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
import java.util.List;

@SessionAttributes({"gebruiker", "voetbalPlaatjes"})
@Controller
public class DubbelenVerwijderenController {
    @Autowired
    GebruikerService gebruikerService;

    @Autowired
    VoetbalPlaatjeService voetbalPlaatjeService;

    @GetMapping("naarDubbelenVerwijderen")
    public String naarDubbelenVerwijderenHandler(Model model){
        Gebruiker gebruiker = (Gebruiker) model.getAttribute("gebruiker");
        gebruiker = gebruikerService.getGebruikerByGebruikersnaam(gebruiker.getGebruikersnaam());
        List<VoetbalPlaatje> dubbelen = gebruiker.getDubbelen();

        model.addAttribute("voetbalPlaatjes", dubbelen);

        return "dubbelen_verwijderen";
    }

    @PostMapping("dubbelenVerwijderen")
    public String dubbelenVerwijderen(@RequestParam(name = "ids") String ids, Model model){
        List<VoetbalPlaatje> voetbalPlaatjes = new ArrayList<>();
        if(ids.length()>0) {
            voetbalPlaatjes = voetbalPlaatjeService.getVoetbalPlaatsByCSString(ids);
        }
        Gebruiker gebruiker = (Gebruiker)model.getAttribute("gebruiker");
        gebruiker = gebruikerService.getGebruikerByGebruikersnaam(gebruiker.getGebruikersnaam());

        for (VoetbalPlaatje plaatje : voetbalPlaatjes) {
            gebruiker.getDubbelen().remove(plaatje);
        }

        gebruikerService.saveGebruiker(gebruiker);
        model.addAttribute("motd", "wijzigingen zijn succesvol doorgevoerd");
        return "thuis_pagina";
    }
}
