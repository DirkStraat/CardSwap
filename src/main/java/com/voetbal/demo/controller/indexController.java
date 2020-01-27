package com.voetbal.demo.controller;

import com.voetbal.demo.model.Gebruiker;
import com.voetbal.demo.service.GebruikerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"gebruiker"})
@Controller
public class indexController {
    @Autowired
    private GebruikerService gebruikerService;

    @GetMapping("naar_lid_worden")
    public String naarLidWordenHandler(Model model){
        return "lid_worden";
    }


    @PostMapping("do_login")
    public String doLoginHandler(@RequestParam(name = "naam") String naam,
                                 @RequestParam(name = "wachtwoord") String wachtwoord,
                                 Model model) {

        Gebruiker gebruiker = gebruikerService.getGebruikerByGebruikersnaam(naam);
        if(gebruiker == null){
            model.addAttribute("motd", "Deze gebruikersnaam komt niet voor in onze Database.");
            return "index.html";
        }

        if (!gebruiker.getWachtwoord().equals(wachtwoord)){
            model.addAttribute("motd", "Gebruikersnaam en wachtwoord komen niet overeen.");
            return "index.html";
        }

        model.addAttribute(gebruiker);
        return "thuis_pagina";

    }
}
