package com.voetbal.demo.model.dao;

import com.voetbal.demo.model.VoetbalPlaatje;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoetbalPlaatjeDao extends CrudRepository<VoetbalPlaatje, Integer> {
    List<VoetbalPlaatje> getAllByJaar(int jaar);
    VoetbalPlaatje getVoetbalPlaatjeByIndex(int index);
}
