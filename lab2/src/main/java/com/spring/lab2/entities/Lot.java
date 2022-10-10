package com.spring.lab2.entities;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Lot implements BaseEntity {
    private static int currentId = 0;
    private Integer id;
    private String lotName;
    private int startPrice;
    @Builder.Default
    private LotStatus status = LotStatus.OPENED;
    private User owner;
    @Builder.Default
    private List<Bid> bids = new ArrayList<>();

    public void addBid(Bid bid) {
        bids.add(bid);
    }
}
