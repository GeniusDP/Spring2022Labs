package com.spring.lab2.controllers;

import com.spring.lab2.models.Bid;
import com.spring.lab2.services.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bids")
@RequiredArgsConstructor
public class BidController {
    private final BidService bidService;

    @GetMapping
    public String getBids(Model model) {
        List<Bid> bids = bidService.getAllBids();
        model.addAllAttributes(bids);
        return "";
    }
}
