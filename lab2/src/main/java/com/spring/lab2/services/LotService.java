package com.spring.lab2.services;

import com.spring.lab2.entities.Lot;
import com.spring.lab2.entities.LotStatus;
import com.spring.lab2.entities.User;
import com.spring.lab2.repositories.LotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LotService {
    private final LotRepository lotRepository;

    public List<Lot> getAllLots(LotStatus status, String filter) {
        return lotRepository.findAll().stream()
                .filter(l -> l.getStatus() != status)
                .filter(l -> l.getLotName().toLowerCase().contains(filter))
                .toList();
    }

    public List<Lot> getAllLotsByOwner(User owner) {
        return lotRepository.findAll().stream().filter(lot -> lot.getOwner().equals(owner)).collect(Collectors.toList());
    }

    public Lot save(Lot lot) {
        return lotRepository.save(lot);
    }

    public Lot findById(Integer id) {
        return lotRepository.findById(id);
    }

    public Lot getLotById(Integer id) {
        return lotRepository.findById(id);
    }
}
