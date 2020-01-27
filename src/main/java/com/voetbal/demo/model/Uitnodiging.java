package com.voetbal.demo.model;

import javax.persistence.*;

@Entity
public class Uitnodiging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uitnodigingId;

    @ManyToOne
    private Gebruiker nodiger;

    @ManyToOne
    private Gebruiker genodigde;

    private String keycode;

    private boolean uitnodigingGeaccepteerd;

    public Uitnodiging() {
        super();
    }

    public Uitnodiging(Gebruiker nodiger, Gebruiker genodigde, String keycode) {
        this.nodiger = nodiger;
        this.genodigde = genodigde;
        this.keycode = keycode;
        this.uitnodigingGeaccepteerd = false;
    }

    public int getUitnodigingId() {
        return uitnodigingId;
    }

    public void setUitnodigingId(int uitnodigingId) {
        this.uitnodigingId = uitnodigingId;
    }

    public Gebruiker getNodiger() {
        return nodiger;
    }

    public void setNodiger(Gebruiker nodiger) {
        this.nodiger = nodiger;
    }

    public Gebruiker getGenodigde() {
        return genodigde;
    }

    public void setGenodigde(Gebruiker genodigde) {
        this.genodigde = genodigde;
    }

    public String getKeycode() {
        return keycode;
    }

    public void setKeycode(String keycode) {
        this.keycode = keycode;
    }

    public boolean isUitnodigingGeaccepteerd() {
        return uitnodigingGeaccepteerd;
    }

    public void setUitnodigingGeaccepteerd(boolean uitnodigingGeaccepteerd) {
        this.uitnodigingGeaccepteerd = uitnodigingGeaccepteerd;
    }


}
