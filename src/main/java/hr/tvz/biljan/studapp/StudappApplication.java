package hr.tvz.biljan.studapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "hr.tvz.biljan")
public class StudappApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudappApplication.class, args);
    }

}

