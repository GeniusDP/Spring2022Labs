package com.spring.lab2.controllers;

import com.spring.lab2.entities.Lot;
import com.spring.lab2.repositories.LotRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lots")
@RequiredArgsConstructor
public class LotsRestController {
  private final LotRepository repository;

  @GetMapping({"/", ""})
  public List<Lot> getAllBids(){
    return repository.findAll();
  }

}
