package com.nickolos.result;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ResultApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResultApplication.class, args);
    }

}
