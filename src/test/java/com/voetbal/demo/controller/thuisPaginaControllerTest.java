package com.voetbal.demo.controller;

import com.voetbal.demo.model.Gebruiker;
import com.voetbal.demo.model.Uitnodiging;
import com.voetbal.demo.service.GebruikerService;
import com.voetbal.demo.service.UitnodigingService;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import static org.mockito.Mockito.*;


public class thuisPaginaControllerTest {


    @Test
    public void wordenVriendenToegevoegd(){
        GebruikerService gebruikerService = mock(GebruikerService.class);
        Gebruiker gebruiker1 = mock(Gebruiker.class);
        Gebruiker gebruiker2 = mock(Gebruiker.class);
        Uitnodiging uitnodiging = mock(Uitnodiging.class);

        when(gebruikerService.getGebruikerByGebruikersnaam(gebruiker1.getGebruikersnaam())).thenReturn(gebruiker1);

        ThuisPaginaController thuisPaginaController = new ThuisPaginaController();


        thuisPaginaController.setGebruikerService(gebruikerService);

        thuisPaginaController.voegVriendenToe(gebruiker1, gebruiker2, uitnodiging);
        verify(gebruikerService, times(2)).saveGebruiker(gebruiker1);
    }

    @Test
    public void inviteRepresentativeHandlerTest(){
        GebruikerService gebruikerService = mock(GebruikerService.class);
        UitnodigingService uitnodigingService = mock(UitnodigingService.class);
        Gebruiker gebruiker1 = mock(Gebruiker.class);
        Gebruiker gebruiker2 = mock(Gebruiker.class);
        Model model = mock(Model.class);
        String keycode = "12345";
        String userName = gebruiker1.getGebruikersnaam();

        when(gebruikerService.getGebruikerByGebruikersnaam(userName)).thenReturn(gebruiker1);
        when(model.getAttribute("gebruiker")).thenReturn(gebruiker2);
        when(uitnodigingService.getUitnodigingByGenodigdeAndNodiger(gebruiker1, gebruiker2)).thenReturn(null);
        ThuisPaginaController thuisPaginaController = new ThuisPaginaController();
        thuisPaginaController.setGebruikerService(gebruikerService);
        thuisPaginaController.setUitnodigingService(uitnodigingService);
        thuisPaginaController.inviteRepresentativeHandler(userName,keycode, model);
        verify(model, times(0)).addAttribute("motd", "Deze gebruikersnaam is niet bekend in het systeem.");
        verify(model, times(0)).addAttribute("motd", "Aantal tekens van koppelcode moet 5 zijn.");
        verify(model, times(0)).addAttribute("motd", "Deze uitnodiging is al eerder naar deze vriend gestuurd.");


    }

    /*@Test
    public void accepteerUitnodigingHandlerTest(){
        //Gebruiker gebruiker1 = mock(Gebruiker.class);
        UitnodigingService uitnodigingService = mock(UitnodigingService.class);
        Model model = mock(Model.class);
        Uitnodiging uitnodiging = mock(Uitnodiging.class);
        String keycode = uitnodiging.getKeycode();
        int invitationId = 123;


        when(uitnodigingService.getUitnodigingByUitnodigingId(invitationId)).thenReturn(uitnodiging);
        ThuisPaginaController thuisPaginaController = new ThuisPaginaController();
        thuisPaginaController.setUitnodigingService(uitnodigingService);
        when(thuisPaginaController.checkKey(keycode, uitnodiging)).thenReturn(true);
        thuisPaginaController.accepteerUitnodigingHandler(keycode, invitationId, model);
        verify(model, times(1)).addAttribute("motd", "Jouw vriend is succesvol toegevoegd.");

    }*/





}
