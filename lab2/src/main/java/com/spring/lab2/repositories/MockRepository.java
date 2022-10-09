package com.spring.lab2.repositories;

import com.spring.lab2.models.Bid;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MockRepository implements BidRepository {
    private List<Bid> bids;

    public MockRepository(LotRepository lotRepository) {
        bids = new ArrayList<>();
        User bidder = new User("user1", "password", new ArrayList<>());
        Random random = new Random();
        lotRepository.findAll().forEach(lot ->
                bids.add(Bid.builder()
                        .id(UUID.randomUUID().toString())
                        .lot(lot)
                        .bidder(bidder)
                        .newBid(lot.getCurrentBid() + 1 + random.nextLong())
                        .build())
        );
    }

    @Override
    public <S extends Bid> S save(S entity) {
        entity.setId(UUID.randomUUID().toString());
        bids.add(entity);
        return entity;
    }

    @Override
    public <S extends Bid> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(entity -> entity.setId(UUID.randomUUID().toString()));
        bids.addAll((Collection<? extends Bid>) entities);
        return entities;
    }

    @Override
    public Optional<Bid> findById(String id) {
        return bids.stream().filter(bid -> bid.getId().equals(id)).findFirst();
    }

    @Override
    public boolean existsById(String id) {
        return bids.stream().anyMatch(bid -> bid.getId().equals(id));
    }

    @Override
    public Iterable<Bid> findAll() {
        return bids;
    }

    @Override
    public Iterable<Bid> findAllById(Iterable<String> ids) {
        Set<String> idsSet = new HashSet<>();
        ids.forEach(idsSet::add);
        return bids.stream().filter(bid -> idsSet.contains(bid.getId())).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return bids.size();
    }

    @Override
    public void deleteById(String id) {
        for (int i = 0; i < bids.size(); i++) {
            if (bids.get(i).getId().equals(id)) {
                bids.remove(i);
                return;
            }
        }
    }

    @Override
    public void delete(Bid entity) {
        for (int i = 0; i < bids.size(); i++) {
            if (bids.get(i).getId().equals(entity.getId())) {
                bids.remove(i);
                return;
            }
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends Bid> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }
}
