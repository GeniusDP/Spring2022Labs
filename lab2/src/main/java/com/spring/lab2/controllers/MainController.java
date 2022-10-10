package com.spring.lab2.controllers;

import com.spring.lab2.entities.Bid;
import com.spring.lab2.entities.Lot;
import com.spring.lab2.entities.LotStatus;
import com.spring.lab2.entities.User;
import com.spring.lab2.services.LotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final LotService lotService;

    @GetMapping({"/", "/all-lots"})
    public String index(Model model, @RequestParam(required = false, defaultValue = "") String filter) {
        model.addAttribute("lots", lotService.getAllLots(LotStatus.CLOSED, filter.toLowerCase()));
        return "all_lots";
    }

    @GetMapping("/create-lot")
    public String createLot() {
        return "create-lot";
    }

    @PostMapping(value = "/create-lot", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createLot(@RequestParam String lotName, @RequestParam Integer startPrice, Model model) {
        lotService.save(Lot.builder().lotName(lotName).startPrice(startPrice).owner(getCurrentUser()).build());
        List<Lot> lots = lotService.getAllLotsByOwner(getCurrentUser());
        model.addAttribute("lots", lots);
        return "redirect:/lots-management";
    }

    @GetMapping("/make-bid")
    public String makeBid(@RequestParam Integer value, @RequestParam Integer lotId) {
        User bidCreator = getCurrentUser();
        Lot lot = lotService.findById(lotId);
        Bid bid = Bid.builder()
                .value(value)
                .creator(bidCreator)
                .lot(lot)
                .build();
        lot.addBid(bid);
        bidCreator.addBid(bid, lot);
        lotService.save(lot);
        return "make_bid";
    }

    @GetMapping("/lots-management")
    public String managingLots(Model model) {
        List<Lot> lots = lotService.getAllLotsByOwner(getCurrentUser());
        model.addAttribute("lots", lots);
        return "lots-management";
    }

    @GetMapping("/lot-editor/{id}")
    public String getLotEditor(@PathVariable Integer id, Model model) {
        Lot lot = lotService.getLotById(id);
        model.addAttribute("lot", lot);
        model.addAttribute("editable", lot.getOwner().equals(getCurrentUser()));
        return "lot-editor";
    }

    @PostMapping(value = "/update-lot/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateLot(@PathVariable Integer id, @RequestParam String lotName, @RequestParam String status, Model model) {
        Lot lot = lotService.getLotById(id);
        lot.setLotName(lotName);
        LotStatus lotStatus = LotStatus.valueOf(status);
        if (lotStatus != LotStatus.SOLD) {
            lot.setStatus(lotStatus);
        }
        lot = lotService.save(lot);
        model.addAttribute("lot", lot);
        model.addAttribute("editable", lot.getOwner().equals(getCurrentUser()));
        return "redirect:/lot-editor/" + id;
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
