package com.spring.lab2.services;

import com.spring.lab2.dto.request.LotCreationDto;
import com.spring.lab2.dto.request.LotUpdateDto;
import com.spring.lab2.dto.request.PageDto;
import com.spring.lab2.entities.Lot;
import com.spring.lab2.entities.LotStatus;
import com.spring.lab2.entities.User;
import com.spring.lab2.exceptions.InvalidLotUpdateException;
import com.spring.lab2.exceptions.UserNotFoundException;
import com.spring.lab2.repositories.LotRepository;
import com.spring.lab2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
public class LotRestService {

    private final LotRepository lotRepository;
    private final UserRepository userRepository;

    public List<Lot> findAll() {
        return lotRepository.findAll();
    }

    public Lot findById(Integer lotId) {
        return lotRepository.findById(lotId);
    }

    public Lot save(LotCreationDto dto) {
        User user = getLotOwnerOrThrow(dto);
        Lot lot = Lot.builder()
                .lotName(dto.getLotName())
                .owner(user)
                .startPrice(dto.getStartPrice())
                .build();
        return lotRepository.save(lot);
    }

    private User getLotOwnerOrThrow(LotCreationDto dto) {
        Optional<User> optionalUser = userRepository.findByUsername(dto.getUsername());
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        return optionalUser.get();
    }

    public Lot update(Integer lotId, LotUpdateDto lotDto) {
        Lot lot = lotRepository.findById(lotId);
        if (lot.getStatus() == LotStatus.SOLD) {
            throw new InvalidLotUpdateException();
        }
        User newOwner = userRepository.findById(lotDto.getUserId());
        lot.setLotName(lotDto.getLotName());
        lot.setStatus(lotDto.getLotStatus());
        lot.setOwner(newOwner);
        lotRepository.save(lot);
        return lot;
    }

    public void deleteById(Integer lotId) {
        lotRepository.findById(lotId);
        lotRepository.deleteById(lotId);
    }

    public List<Lot> findAllPaginated(@Valid PageDto dto) {
        int pageNumber = dto.getPage() - 1;
        int pageSize = dto.getSize();
        return findAll().stream()
                .skip((long) pageNumber * pageSize)
                .limit(pageSize)
                .toList();
    }
}
