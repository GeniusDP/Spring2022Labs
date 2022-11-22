package com.spring.lab2.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Bid implements BaseEntity {

    private Integer id;

    @JsonBackReference
    private User creator;

    @JsonBackReference
    private Lot lot;

    private int value;

    private BidStatus bidStatus;
}
