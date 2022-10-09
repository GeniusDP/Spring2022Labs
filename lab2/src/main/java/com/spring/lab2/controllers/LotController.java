package com.spring.lab2.controllers;

import com.spring.lab2.models.Lot;
import com.spring.lab2.services.LotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/lots")
@RequiredArgsConstructor
public class LotController {
    private final LotService lotService;

    @GetMapping
    public String getLots(Model model) {
        List<Lot> lots = lotService.getAllLots();
        model.addAllAttributes(lots);
        return "";
    }

}
