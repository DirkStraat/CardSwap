package com.voetbal.demo.service;

import com.voetbal.demo.model.Naam;
import com.voetbal.demo.model.dao.NaamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NaamService {
    @Autowired
    private NaamDao naamDao;

    public void saveNaam(Naam naam){
        naamDao.save(naam);
    }
}
