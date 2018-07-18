package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan({"controllers", "scrapper"})
@EnableScheduling
public class AppInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AppInitializer.class, args);
     }
}
