package com.voetbal.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Gebruiker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int gebruikerId;

    @OneToOne
    private Naam naam;
    private String gebruikersnaam;
    private String email;
    private String wachtwoord;

    @ManyToMany
    private List<VoetbalPlaatje> dubbelen;

    @ManyToMany
    private List<VoetbalPlaatje> ontbrekenden;

    @ManyToMany
    private List<Gebruiker> vrienden;

    @OneToMany
    private List<Uitnodiging> uitnodigingenVerstuurd;

    @OneToMany
    private List<Uitnodiging> uitnodigingenOntvangen;


    public Gebruiker() {
        super();
    }

    public Gebruiker(Naam naam, String gebruikersnaam, String email) {
        this.naam = naam;
        this.gebruikersnaam = gebruikersnaam;
        this.email = email;
        dubbelen = new ArrayList<>();
        ontbrekenden = new ArrayList<>();
        uitnodigingenOntvangen= new ArrayList<>();
        uitnodigingenVerstuurd = new ArrayList<>();
        vrienden = new ArrayList<>();
    }

    public Naam getNaam() {
        return naam;
    }

    public void setNaam(Naam naam) {
        this.naam = naam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<VoetbalPlaatje> getDubbelen() {
        return dubbelen;
    }

    public void setDubbelen(List<VoetbalPlaatje> dubbelen) {
        this.dubbelen = dubbelen;
    }

    public List<VoetbalPlaatje> getOntbrekenden() {
        return ontbrekenden;
    }

    public void setOntbrekenden(List<VoetbalPlaatje> ontbrekenden) {
        this.ontbrekenden = ontbrekenden;
    }

    public List<Gebruiker> getVrienden() {
        return vrienden;
    }

    public void setVrienden(List<Gebruiker> vrienden) {
        this.vrienden = vrienden;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public int getGebruikerId() {
        return gebruikerId;
    }

    public void setGebruikerId(int gebruikerId) {
        this.gebruikerId = gebruikerId;
    }

    public List<Uitnodiging> getUitnodigingenVerstuurd() {
        return uitnodigingenVerstuurd;
    }

    public void setUitnodigingenVerstuurd(List<Uitnodiging> uitnodigingenVerstuurd) {
        this.uitnodigingenVerstuurd = uitnodigingenVerstuurd;
    }

    public List<Uitnodiging> getUitnodigingenOntvangen() {
        return uitnodigingenOntvangen;
    }

    public void setUitnodigingenOntvangen(List<Uitnodiging> uitnodigingenOntvangen) {
        this.uitnodigingenOntvangen = uitnodigingenOntvangen;
    }
}
