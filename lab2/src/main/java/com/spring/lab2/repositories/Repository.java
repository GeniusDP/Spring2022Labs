package com.spring.lab2.repositories;

import java.util.List;

public interface Repository<T> {

    T save(T value);

    T findById(int id);

    List<T> findAll();

}
