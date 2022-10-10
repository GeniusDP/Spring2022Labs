package com.spring.lab2.repositories;

import com.spring.lab2.entities.BaseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractRepository<T extends BaseEntity> implements Repository<T>{
    protected final Map<Integer, T> map = new HashMap<>();
    protected int currentId = 0;

    @Override
    public T save(T value) {
        if (value.getId() != null && map.containsKey(value.getId())) {
            map.put(value.getId(), value);
            return value;
        }
        value.setId(currentId);
        map.put(value.getId(), value);
        currentId++;
        return value;
    }

    @Override
    public T findById(int id) {
        return map.get(id);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(map.values());
    }
}
