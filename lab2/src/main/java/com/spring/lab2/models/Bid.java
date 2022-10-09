package com.spring.lab2.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

@Data
@Builder
public class Bid {
    private String id;
    private Lot lot;
    private User bidder;
    private Long newBid;
}
