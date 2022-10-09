package com.spring.lab2.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

@Data
@Builder
public class Lot {
    private String id;
    private String name;
    private Long initialBid;
    private Long currentBid;
    private User owner;
    private LotStatus lotStatus;
}
