package kpi.spring2022.labs.first_lab.runners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class FirstCommandRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("First");
    }
}
