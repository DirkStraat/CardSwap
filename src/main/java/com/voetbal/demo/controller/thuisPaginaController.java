package com.voetbal.demo.controller;

import com.voetbal.demo.model.Gebruiker;
import com.voetbal.demo.model.Uitnodiging;
import com.voetbal.demo.service.GebruikerService;
import com.voetbal.demo.service.UitnodigingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@SessionAttributes({"gebruiker", "uitnodigingen"})
@Controller
public class thuisPaginaController {
    private final int LENGTE_KOPPELCODE = 5;

    @Autowired
    private GebruikerService gebruikerService;

    @Autowired
    private UitnodigingService uitnodigingService;

    @PostMapping("vriend_uitnodigen")
    public String inviteRepresentativeHandler(@RequestParam(name = "user_name") String userName,
                                              @RequestParam(name = "keycode") String keycode , Model model) {
        Gebruiker genodigde = gebruikerService.getGebruikerByGebruikersnaam(userName);
        Gebruiker nodiger = (Gebruiker) model.getAttribute("gebruiker");
        Uitnodiging uitnodiging = uitnodigingService.getUitnodigingByGenodigdeAndNodiger(genodigde, nodiger);

        if (genodigde == null){
            model.addAttribute("motd", "Deze gebruikersnaam is niet bekend in het systeem.");
        } else if (keycode.length() != LENGTE_KOPPELCODE){
            model.addAttribute("motd", "Aantal tekens van koppelcode moet 5 zijn.");
        } else if (uitnodiging != null){
            model.addAttribute("motd", "Deze uitnodiging is al eerder naar deze vriend gestuurd.");
        } else {
            uitnodigingVersturen(keycode, model, genodigde, nodiger);
        }
        return thuisPaginaHandler(model);
    }

    @PostMapping("accepteer_uitnodiging")
    public String accepteerUitnodigingHandler(@RequestParam(name = "keycode") String keycode,
                                              @RequestParam(name = "invitationId") int invitationId,
                                              Model model){
        Gebruiker genodigde = (Gebruiker)model.getAttribute("gebruiker");
        Uitnodiging uitnodiging = uitnodigingService.getUitnodigingByUitnodigingId(invitationId);
        if(uitnodiging==null){
            model.addAttribute("De uitnodiging werd niet gevonden in de database");
            return thuisPaginaHandler(model);
        }

        if(!checkKey(keycode, uitnodiging)){
            model.addAttribute("motd", "De ingevoerde koppelcode is niet juist.");
            return thuisPaginaHandler(model);
        }

        Gebruiker nodiger = uitnodiging.getNodiger();
        voegVriendenToe(nodiger, genodigde, uitnodiging);
        saveGeaccepteerdeUitnodiging(uitnodiging);
        model.addAttribute("motd", "Jouw vriend is succesvol toegevoegd.");
        return thuisPaginaHandler(model);
    }

    @GetMapping("logout")
    public String logoutHandler(){
        return "index";
    }

    private void uitnodigingVersturen(String keycode, Model model, Gebruiker genodigde, Gebruiker nodiger) {
        Uitnodiging uitnodiging;
        uitnodiging = new Uitnodiging(nodiger, genodigde, keycode);
        genodigde = gebruikerService.getGebruikerByGebruikersnaam(genodigde.getGebruikersnaam());
        nodiger = gebruikerService.getGebruikerByGebruikersnaam(nodiger.getGebruikersnaam());
        uitnodigingService.nodigVriendUit(uitnodiging);
        genodigde.getUitnodigingenOntvangen().add(uitnodiging);
        nodiger.getUitnodigingenVerstuurd().add(uitnodiging);
        gebruikerService.saveGebruiker(genodigde);
        gebruikerService.saveGebruiker(nodiger);
        model.addAttribute("motd", "De uitnodiging is naar jouw vriend verstuurd. Deze kan de uitnodiging accepteren met de koppelcode.");
    }

    private boolean checkKey(String keycode, Uitnodiging uitnodiging){
        if (keycode.length() != LENGTE_KOPPELCODE){
            return false;
        } else if (!keycode.equals(uitnodiging.getKeycode()) ){
            return false;
        } else return true;
    }

    private String thuisPaginaHandler(Model model){
        Gebruiker gebruiker = (Gebruiker) model.getAttribute("gebruiker");
        model.addAttribute("gebruiker", gebruikerService.getGebruikerByGebruikersnaam(gebruiker.getGebruikersnaam()));
        return "thuis_pagina";
    }

    private void voegVriendenToe(Gebruiker nodiger, Gebruiker genodigde, Uitnodiging uitnodiging){
        genodigde = gebruikerService.getGebruikerByGebruikersnaam(genodigde.getGebruikersnaam());
        genodigde.getVrienden().add(nodiger);
        genodigde.getUitnodigingenOntvangen().remove(uitnodiging);
        nodiger.getUitnodigingenVerstuurd().remove(uitnodiging);
        for (Uitnodiging uitnodiging1: genodigde.getUitnodigingenOntvangen()) {
            System.out.println(uitnodiging1.getUitnodigingId());
        }
        nodiger.getVrienden().add(genodigde);

        gebruikerService.saveGebruiker(nodiger);
        gebruikerService.saveGebruiker(genodigde);
    }

    private void saveGeaccepteerdeUitnodiging(Uitnodiging uitnodiging){
        uitnodiging.setUitnodigingGeaccepteerd(true);
        uitnodigingService.save(uitnodiging);

    }

}
