package com.voetbal.demo.model;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;

@Entity
public class VoetbalPlaatje implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int index;

    private int nummer;
    private String naam;
    private String team;
    private int jaar;

    @ManyToMany (mappedBy = "dubbelen")
    private List<Gebruiker> heeftDubbel;

    @ManyToMany (mappedBy = "ontbrekenden")
    private List<Gebruiker> heeftNodig;

    public VoetbalPlaatje() {
        super();
    }

    public VoetbalPlaatje(int nummer, String naam, String team) {
        this.nummer = nummer;
        this.naam = naam;
        this.team = team;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getJaar() {
        return jaar;
    }

    public void setJaar(int jaar) {
        this.jaar = jaar;
    }

    public List<Gebruiker> getHeeftDubbel() {
        return heeftDubbel;
    }

    public void setHeeftDubbel(List<Gebruiker> heeftDubbel) {
        this.heeftDubbel = heeftDubbel;
    }

    public List<Gebruiker> getHeeftNodig() {
        return heeftNodig;
    }

    public void setHeeftNodig(List<Gebruiker> heeftNodig) {
        this.heeftNodig = heeftNodig;
    }

    @Override
    public int compareTo(Object o) {
        VoetbalPlaatje plaatje2 = (VoetbalPlaatje)o;
        int nr2 = plaatje2.nummer;
        return this.nummer-nr2;    }
}
