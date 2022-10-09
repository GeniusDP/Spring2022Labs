package com.spring.lab2.repositories;

import com.spring.lab2.models.Lot;
import com.spring.lab2.models.LotStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MockLotRepository implements LotRepository {
    private List<Lot> lots;

    public MockLotRepository() {
        Random random = new Random();
        lots = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            long initialBid = random.nextLong(1000);
            lots.add(Lot.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Lot " + (i + 1))
                    .initialBid(initialBid)
                    .currentBid(initialBid)
                    .lotStatus(LotStatus.OPEN)
                    .owner(new User("user" + (i + 1), "password", new ArrayList<>()))
                    .build());
        }
    }

    @Override
    public <S extends Lot> S save(S entity) {
        entity.setId(UUID.randomUUID().toString());
        lots.add(entity);
        return entity;
    }

    @Override
    public <S extends Lot> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(entity -> entity.setId(UUID.randomUUID().toString()));
        lots.addAll((Collection<? extends Lot>) entities);
        return entities;
    }

    @Override
    public Optional<Lot> findById(String id) {
        return lots.stream().filter(lot -> lot.getId().equals(id)).findFirst();
    }

    @Override
    public boolean existsById(String id) {
        return lots.stream().anyMatch(lot -> lot.getId().equals(id));
    }

    @Override
    public Iterable<Lot> findAll() {
        return lots;
    }

    @Override
    public Iterable<Lot> findAllById(Iterable<String> ids) {
        Set<String> idsSet = new HashSet<>();
        ids.forEach(idsSet::add);
        return lots.stream().filter(lot -> idsSet.contains(lot.getId())).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return lots.size();
    }

    @Override
    public void deleteById(String id) {
        for (int i = 0; i < lots.size(); i++) {
            if (lots.get(i).getId().equals(id)) {
                lots.remove(i);
                return;
            }
        }
    }

    @Override
    public void delete(Lot entity) {
        for (int i = 0; i < lots.size(); i++) {
            if (lots.get(i).getId().equals(entity.getId())) {
                lots.remove(i);
                return;
            }
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends Lot> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        lots = new ArrayList<>();
    }
}
