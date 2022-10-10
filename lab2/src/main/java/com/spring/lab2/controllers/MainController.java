package com.spring.lab2.controllers;

import com.spring.lab2.entities.Lot;
import com.spring.lab2.entities.LotStatus;
import com.spring.lab2.repositories.LotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final LotRepository lotRepository;

    @GetMapping({"/", "/all-lots"})
    public String index(Model model, @RequestParam(required = false) String filter) {
        if(filter == null){
            filter = "";
        }
        filter = filter.toLowerCase();
        String finalFilter = filter;//only for lambda

        List<Lot> allLots = lotRepository.findAll().stream()
                .filter(l -> l.getStatus() != LotStatus.CLOSED)
                .filter(l -> l.getLotName().toLowerCase().contains(finalFilter))
                .toList();
        model.addAttribute("lots", allLots);
        return "all_lots";
    }

}
