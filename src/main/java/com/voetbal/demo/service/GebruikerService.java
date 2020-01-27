package com.voetbal.demo.service;

import com.voetbal.demo.model.Gebruiker;
import com.voetbal.demo.model.dao.GebruikerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GebruikerService {
    @Autowired
    GebruikerDao gebruikerDao;

    public void saveGebruiker(Gebruiker gebruiker){
        gebruikerDao.save(gebruiker);
    }

    public Gebruiker getGebruikerByGebruikersnaam(String gebruikersnaam){
        return gebruikerDao.getGebruikerByGebruikersnaam(gebruikersnaam);
    }
}
