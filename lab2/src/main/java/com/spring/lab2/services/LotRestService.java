package com.spring.lab2.services;

import com.spring.lab2.dto.request.LotDto;
import com.spring.lab2.entities.Lot;
import com.spring.lab2.entities.User;
import com.spring.lab2.exceptions.LotNotFoundException;
import com.spring.lab2.exceptions.UserNotFoundException;
import com.spring.lab2.repositories.LotRepository;
import com.spring.lab2.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LotRestService {

  private final LotRepository lotRepository;
  private final UserRepository userRepository;

  public List<Lot> findAll() {
    return lotRepository.findAll();
  }

  public Lot findById(Integer lotId) {
    Lot lot = lotRepository.findById(lotId);
    if(lot == null){
      throw new LotNotFoundException();
    }
    return lot;
  }

  public Lot save(LotDto dto) {
    Optional<User> optionalUser = userRepository.findByUsername(dto.getUsername());
    if (optionalUser.isEmpty()) {
      throw new UserNotFoundException();
    }
    User user = optionalUser.get();
    Lot lot = Lot.builder()
      .lotName(dto.getLotName())
      .owner(user)
      .startPrice(dto.getStartPrice())
      .build();
    return lotRepository.save(lot);
  }
}
