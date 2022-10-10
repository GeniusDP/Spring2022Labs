package com.spring.lab2.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Bid implements BaseEntity{
    private Integer id;
    private User creator;
    private Lot lot;
    private int value;
}
