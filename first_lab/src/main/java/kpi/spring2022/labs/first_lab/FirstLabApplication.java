package kpi.spring2022.labs.first_lab;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;

@SpringBootApplication
@Order(0)
public class FirstLabApplication implements CommandLineRunner {

    public static void main(String[] args) {
        System.out.println("Begin of main");
        SpringApplication.run(FirstLabApplication.class, args);
        System.out.println("End of main");
    }

    @Override
    public void run(String... args) {
        System.out.println("Hello from Spring Boot!");
    }
}
