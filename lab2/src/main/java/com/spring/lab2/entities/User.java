package com.spring.lab2.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@ToString
public class User implements UserDetails, BaseEntity {
    private Integer id;
    private final String name;
    private final String password;
    @ToString.Exclude
    private List<Lot> lots;
    @ToString.Exclude
    private List<Bid> madeBids;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        lots = new ArrayList<>();
        madeBids = new ArrayList<>();
    }

    public void addLot(Lot lot) {
        lots.add(lot);
        lot.setOwner(this);
    }

    public void addBid(Bid bid, Lot lot) {
        madeBids.add(bid);
        lot.addBid(bid);
        bid.setCreator(this);
    }

    public void makeBidToLot(Bid bid, Lot lot) {
        bid.setLot(lot);
        lot.addBid(bid);
        madeBids.add(bid);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
