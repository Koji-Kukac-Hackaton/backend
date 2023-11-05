package com.codebooq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
@EnableAsync
@CrossOrigin(origins = "*")
public class CodebooqApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodebooqApplication.class, args);
    }

}
