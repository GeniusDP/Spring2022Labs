package com.spring.lab2.services;

import com.spring.lab2.models.Bid;
import com.spring.lab2.repositories.BidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BidService {
    private final BidRepository bidRepository;

    public List<Bid> getAllBids() {
        return (List<Bid>) bidRepository.findAll();
    }
}
