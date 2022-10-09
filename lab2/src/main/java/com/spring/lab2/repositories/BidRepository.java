package com.spring.lab2.repositories;

import com.spring.lab2.models.Bid;
import org.springframework.data.repository.CrudRepository;

public interface BidRepository extends CrudRepository<Bid, String> {
}
