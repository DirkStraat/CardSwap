package com.voetbal.demo.model.dao;

import com.voetbal.demo.model.Gebruiker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GebruikerDao extends CrudRepository<Gebruiker, Integer> {
    public Gebruiker getGebruikerByGebruikersnaam(String gebruikersnaam);
}
