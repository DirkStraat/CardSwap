package com.voetbal.demo.service;

import com.voetbal.demo.model.VoetbalPlaatje;
import com.voetbal.demo.model.dao.VoetbalPlaatjeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class VoetbalPlaatjeService {
    private static final String COMMA_DELIMITER = ";";
    private static final int JAAR = 2020;
    @Autowired
    VoetbalPlaatjeDao voetbalPlaatjeDao;

    public String fillDatabase() {
        List<List<String>> voetbalplaatjes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Lijst_nummer_naam_club.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                voetbalplaatjes.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveVoetbalplaatjes(voetbalplaatjes);

        return "login";
    }

    private void saveVoetbalplaatjes(List<List<String>> records) {
        for (int i = 0; i < records.size(); i++) {
            String nummerString = records.get(i).get(0);
            nummerString = nummerString.replaceAll("\uFEFF", "");
            String naam = records.get(i).get(1);
            String club = records.get(i).get(2);
            int nummer = Integer.parseInt(nummerString);
            VoetbalPlaatje plaatje = new VoetbalPlaatje(nummer, naam, club);
            plaatje.setJaar(JAAR);
            voetbalPlaatjeDao.save(plaatje);
        }
    }

    public List<VoetbalPlaatje> getVoetbalPlaatjes(){
        return  voetbalPlaatjeDao.getAllByJaar(0);
    }

    public List<VoetbalPlaatje> getVoetbalPlaatsByCSString(String ids){
        List<String> items = Arrays.asList(ids.split(","));
        List<VoetbalPlaatje> voetbalPlaatjes = new ArrayList<>();

        for (String item : items){
            int index = Integer.parseInt(item);
            VoetbalPlaatje plaatje = getVoetbalPlaatjeByIndex(index);
            voetbalPlaatjes.add(plaatje);
        }
        return voetbalPlaatjes;
    }

    public VoetbalPlaatje getVoetbalPlaatjeByIndex(int index){
        return voetbalPlaatjeDao.getVoetbalPlaatjeByIndex(index);
    }
}
