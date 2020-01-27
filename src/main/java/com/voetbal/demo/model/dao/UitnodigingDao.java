package com.voetbal.demo.model.dao;

import com.voetbal.demo.model.Gebruiker;
import com.voetbal.demo.model.Uitnodiging;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public interface UitnodigingDao extends CrudRepository<Uitnodiging, Integer> {
    Uitnodiging getUitnodigingByGenodigdeAndNodiger(Gebruiker genodigde, Gebruiker nodiger);
    Uitnodiging getUitnodigingByUitnodigingId(int uitnodigingId);
    List<Uitnodiging> getUitnodigingsByGenodigdeAndUitnodigingGeaccepteerdIsFalse(Gebruiker genodigde);

}
