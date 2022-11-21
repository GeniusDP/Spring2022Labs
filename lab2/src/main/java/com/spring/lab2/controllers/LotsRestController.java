package com.spring.lab2.controllers;

import com.spring.lab2.entities.Lot;
import com.spring.lab2.services.LotRestService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lots")
@RequiredArgsConstructor
public class LotsRestController {
  private final LotRestService lotRestService;

  @GetMapping({"/", ""})
  public List<Lot> getAllLots(){
    return lotRestService.findAll();
  }

  @GetMapping("/{lotId}")
  public Lot getLotById(@PathVariable Integer lotId){
    return lotRestService.findById(lotId);
  }
  
}
