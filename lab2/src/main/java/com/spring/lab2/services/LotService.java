package com.spring.lab2.services;

import com.spring.lab2.models.Lot;
import com.spring.lab2.repositories.LotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LotService {
    private final LotRepository lotRepository;

    public List<Lot> getAllLots() {
        return (List<Lot>) lotRepository.findAll();
    }
}
