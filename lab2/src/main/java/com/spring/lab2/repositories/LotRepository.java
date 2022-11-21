package com.spring.lab2.repositories;

import com.spring.lab2.entities.Lot;
import org.springframework.stereotype.Repository;

@Repository
public class LotRepository extends AbstractRepository<Lot>{

  public void deleteById(Integer lotId) {
    map.remove(lotId);
  }

}
