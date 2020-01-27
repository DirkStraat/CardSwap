package com.voetbal.demo.controller;

import com.voetbal.demo.service.VoetbalPlaatjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FdbController {
    @Autowired
    VoetbalPlaatjeService voetbalPlaatjeService;

    @GetMapping("fdb")
    public String fdbHandler(){
        voetbalPlaatjeService.fillDatabase();
        return "index";
    }


}
