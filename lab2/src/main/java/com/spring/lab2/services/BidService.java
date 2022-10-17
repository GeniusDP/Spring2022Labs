package com.spring.lab2.services;

import com.spring.lab2.entities.Bid;
import com.spring.lab2.repositories.BidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BidService {
    private final BidRepository bidRepository;

    public List<Bid> getAllBidsOfLot(Integer lotId) {
        return bidRepository.findAll().stream().filter(bid -> bid.getLot().getId().equals(lotId)).collect(Collectors.toList());
    }

    public Bid save(Bid bid) {
        return bidRepository.save(bid);
    }

    public Bid getBidById(Integer id) {
        return bidRepository.findById(id);
    }
}
