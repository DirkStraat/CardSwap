package com.voetbal.demo.model.dao;

import com.voetbal.demo.model.Naam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaamDao extends CrudRepository<Naam, Integer> {
}
