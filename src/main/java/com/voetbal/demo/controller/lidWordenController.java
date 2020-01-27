package com.voetbal.demo.controller;

import com.voetbal.demo.model.Gebruiker;
import com.voetbal.demo.model.Naam;
import com.voetbal.demo.service.GebruikerService;
import com.voetbal.demo.service.NaamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"gebruiker"})
@Controller
public class lidWordenController {
    @Autowired
    NaamService naamService;

    @Autowired
    GebruikerService gebruikerService;

    @Autowired
    AllePlaatjesController allePlaatjesController;

    @PostMapping("lid_worden")
    public String lidWordenHandler(@RequestParam(name = "email") String email,
                                   @RequestParam(name = "gebruikersnaam") String gebruikersnaam,
                                   @RequestParam(name = "wachtwoord") String wachtwoord,
                                   @RequestParam(name = "wachtwoord2") String wachtwoord2,
                                   @RequestParam(name = "voornaam") String voornaam,
                                   @RequestParam(name = "tussenvoegsel") String tussenvoegsel,
                                   @RequestParam(name = "achternaam") String achternaam,
                                   Model model){
        if (!wachtwoord.equals(wachtwoord2)){
            model.addAttribute("motd", "De door jou ingevulde wachtwoorden komen niet overeen.");
            return "lid_worden";
        }

        if (gebruikerService.getGebruikerByGebruikersnaam(gebruikersnaam)!=null){
            model.addAttribute("motd", "Deze gebruikersnaam is al in gebruik.");
            return "lid_worden";
        }

        Naam naam = new Naam(voornaam, tussenvoegsel, achternaam);
        Gebruiker gebruiker = new Gebruiker(naam, gebruikersnaam, email);
        gebruiker.setWachtwoord(wachtwoord);
        naamService.saveNaam(naam);
        gebruikerService.saveGebruiker(gebruiker);
        model.addAttribute("gebruiker", gebruiker);
        allePlaatjesController.allePlaatjesHandler(model);
        return "alle_plaatjes";
    }


}
