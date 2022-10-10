package com.spring.lab2.entities;

import lombok.Data;

@Data
public class Lot {
    private static int currentId = 0;
    private int id;
    private String lotName;
    private int startPrice;
    private User owner;

}
