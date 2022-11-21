package com.spring.lab2.services;

import com.spring.lab2.entities.Lot;
import com.spring.lab2.exceptions.LotNotFoundException;
import com.spring.lab2.repositories.LotRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LotRestService {

  private final LotRepository repository;

  public List<Lot> findAll() {
    return repository.findAll();
  }

  public Lot findById(Integer lotId) {
    Lot lot = repository.findById(lotId);
    if(lot == null){
      throw new LotNotFoundException();
    }
    return lot;
  }
}
