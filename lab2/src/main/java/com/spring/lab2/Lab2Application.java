package com.spring.lab2;

import com.spring.lab2.entities.Lot;
import com.spring.lab2.entities.User;
import com.spring.lab2.repositories.LotRepository;
import com.spring.lab2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab2Application implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LotRepository lotRepository;

    public static void main(String[] args) {
        SpringApplication.run(Lab2Application.class, args);
    }

    @Override
    public void run(String... args) {
        User bogdan = new User("bogdan", "pass");
        Lot computer = Lot.builder().lotName("computer").owner(bogdan).startPrice(1300).build();
        Lot mobilePhone = Lot.builder().lotName("mobile phone").owner(bogdan).startPrice(450).build();
        lotRepository.save(computer);
        lotRepository.save(mobilePhone);
        bogdan.addLot(computer);
        bogdan.addLot(mobilePhone);
        userRepository.save(bogdan);

        User vlad = new User("vlad", "pass");
        userRepository.save(vlad);

    }
}
