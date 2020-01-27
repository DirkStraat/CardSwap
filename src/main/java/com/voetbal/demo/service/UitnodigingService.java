package com.voetbal.demo.service;

import com.voetbal.demo.model.Gebruiker;
import com.voetbal.demo.model.Uitnodiging;
import com.voetbal.demo.model.dao.UitnodigingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UitnodigingService {
    @Autowired
    private UitnodigingDao uitnodigingDao;

    public Uitnodiging getUitnodigingByGenodigdeAndNodiger(Gebruiker genodigde, Gebruiker nodiger){
        return uitnodigingDao.getUitnodigingByGenodigdeAndNodiger(genodigde, nodiger);
    }

    public void nodigVriendUit(Uitnodiging uitnodiging){
        uitnodigingDao.save(uitnodiging);
    }

    public Uitnodiging getUitnodigingByUitnodigingId(int uitnodigingId) {
        return uitnodigingDao.getUitnodigingByUitnodigingId(uitnodigingId);
    }

    public void save(Uitnodiging uitnodiging){
        uitnodigingDao.save(uitnodiging);
    }

    public List<Uitnodiging> getUitnodigingsByGenodigdeAndUitnodigingGeaccepteerdIsFalse(Gebruiker genodigde){
        return uitnodigingDao.getUitnodigingsByGenodigdeAndUitnodigingGeaccepteerdIsFalse(genodigde);
    }
}
