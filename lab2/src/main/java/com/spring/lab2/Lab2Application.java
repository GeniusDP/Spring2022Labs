package com.spring.lab2;

import com.spring.lab2.entities.Bid;
import com.spring.lab2.entities.BidStatus;
import com.spring.lab2.entities.Lot;
import com.spring.lab2.entities.User;
import com.spring.lab2.repositories.BidRepository;
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

    @Autowired
    private BidRepository bidRepository;

    public static void main(String[] args) {
        SpringApplication.run(Lab2Application.class, args);
    }

    @Override
    public void run(String... args) {
        User bogdan = new User("bogdan", "pass");
        Lot computer = Lot.builder().lotName("computer").owner(bogdan).startPrice(1300).build();
        Lot mobilePhone = Lot.builder()
          .lotName("Mobile phone")
          .owner(bogdan)
          .startPrice(450)
          .build();
        Lot javaPersistenceWithHibernate = Lot.builder()
          .lotName("Java Persistence With Hibernate")
          .owner(bogdan)
          .startPrice(100)
          .build();
        Lot noteBook = Lot.builder()
          .lotName("Asus Vivobook 15'")
          .owner(bogdan)
          .startPrice(800)
          .build();
        Lot powerBank = Lot.builder()
          .lotName("Power bank 10'000 mAH")
          .owner(bogdan)
          .startPrice(30)
          .build();
        lotRepository.save(computer);
        lotRepository.save(mobilePhone);
        lotRepository.save(javaPersistenceWithHibernate);
        lotRepository.save(noteBook);
        lotRepository.save(powerBank);
        bogdan.addLot(computer);
        bogdan.addLot(mobilePhone);
        bogdan.addLot(javaPersistenceWithHibernate);
        bogdan.addLot(noteBook);
        bogdan.addLot(powerBank);
        userRepository.save(bogdan);


        User vlad = new User("vlad", "pass");
        Lot springInActionBook = Lot.builder()
          .lotName("Spring In Action 6-th Edition")
          .owner(vlad)
          .startPrice(70)
          .build();
        Lot kotlinInActionBook = Lot.builder()
          .lotName("Kotlin In Action 4-th Edition")
          .owner(vlad)
          .startPrice(80)
          .build();
        Lot scalaInActionBook = Lot.builder()
          .lotName("Scala In Action 3-th Edition")
          .owner(vlad)
          .startPrice(65)
          .build();
        lotRepository.save(springInActionBook);
        lotRepository.save(kotlinInActionBook);
        lotRepository.save(scalaInActionBook);
        vlad.addLot(springInActionBook);
        vlad.addLot(kotlinInActionBook);
        vlad.addLot(scalaInActionBook);
        userRepository.save(vlad);

        Bid computerBid1 = Bid.builder()
          .lot(computer)
          .creator(vlad)
          .bidStatus(BidStatus.NOT_PROCESSED)
          .value(120)
          .build();
        Bid computerBid2 = Bid.builder()
          .lot(computer)
          .creator(vlad)
          .bidStatus(BidStatus.ACCEPTED)
          .value(140)
          .build();
        bidRepository.save(computerBid1);
        bidRepository.save(computerBid2);
        computer.addBid(computerBid1);
        computer.addBid(computerBid2);


    }
}
