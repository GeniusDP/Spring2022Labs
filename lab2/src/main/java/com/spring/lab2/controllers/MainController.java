package com.spring.lab2.controllers;

import com.spring.lab2.entities.*;
import com.spring.lab2.services.BidService;
import com.spring.lab2.services.LotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final LotService lotService;
    private final BidService bidService;

    @GetMapping({"/", "/all-lots"})
    public String index(Model model, @RequestParam(required = false, defaultValue = "") String filter) {
        model.addAttribute("lots", lotService.getAllLots(LotStatus.OPENED, filter.toLowerCase()));
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

    @GetMapping("/view-bids")
    public String viewBidsOfLot(@RequestParam("lot_id") Integer lotId, Model model) {
        List<Bid> bids = bidService.getAllBidsOfLot(lotId).stream()
                .filter(bid -> BidStatus.NOT_PROCESSED == bid.getBidStatus())
                .collect(Collectors.toList());
        model.addAttribute("bids", bids);
        return "bids-view";
    }

    @PostMapping("/bids/{id}/accept")
    public String acceptBid(@PathVariable Integer id, Model model) {
        Bid bid = bidService.getBidById(id);
        bid.setBidStatus(BidStatus.ACCEPTED);
        Lot lot = bid.getLot();
        lot.setStatus(LotStatus.SOLD);
        bidService.save(bid);
        lotService.save(lot);
        model.addAttribute("lots", lotService.getAllLotsByOwner(getCurrentUser()));
        return "redirect:/lots-management";
    }

    @PostMapping("/bids/{id}/decline")
    public String declineBid(@PathVariable Integer id, HttpServletRequest request) {
        Bid bid = bidService.getBidById(id);
        bid.setBidStatus(BidStatus.DECLINED);
        bidService.save(bid);
        return "redirect:" + request.getHeader(HttpHeaders.REFERER);
    }

    @PostMapping(value = "/make-bid", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String makeBid(@RequestParam("new-price") Integer value, @RequestParam("lot-id") Integer lotId) {
        User bidCreator = getCurrentUser();
        Lot lot = lotService.findById(lotId);
        if (!(LotStatus.OPENED == lot.getStatus())) {
            throw new IllegalArgumentException("Can't make a bid for the closed lot");
        }
        int minimalPrice = lot.getBiggestBid();
        if(minimalPrice >= value){
            return "illegal_bid";
        }

        Bid bid = Bid.builder()
                .value(value)
                .creator(bidCreator)
                .lot(lot)
                .bidStatus(BidStatus.NOT_PROCESSED)
                .build();

        lot.addBid(bid);
        bidCreator.addBid(bid, lot);
        bidService.save(bid);
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
        if (LotStatus.SOLD == lot.getStatus()) {
            throw new IllegalArgumentException("Can't update a lot with status SOLD");
        }
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
