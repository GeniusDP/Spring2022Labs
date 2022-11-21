package com.spring.lab2.repositories;

import com.spring.lab2.entities.Lot;
import com.spring.lab2.exceptions.LotNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class LotRepository extends AbstractRepository<Lot> {

    @Override
    public Lot findById(int id) {
        checkLotExists(id);
        return super.findById(id);
    }

    public void deleteById(Integer lotId) {
        checkLotExists(lotId);
        map.remove(lotId);
    }

    private void checkLotExists(int id) {
        if (!map.containsKey(id)) {
            throw new LotNotFoundException();
        }
    }

}
