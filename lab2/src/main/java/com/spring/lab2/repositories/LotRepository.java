package com.spring.lab2.repositories;

import com.spring.lab2.models.Lot;
import org.springframework.data.repository.CrudRepository;

public interface LotRepository extends CrudRepository<Lot, String> {
}
