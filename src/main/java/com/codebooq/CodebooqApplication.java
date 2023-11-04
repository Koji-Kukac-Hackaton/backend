package com.codebooq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
@EnableAsync
public class CodebooqApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodebooqApplication.class, args);
    }

}
