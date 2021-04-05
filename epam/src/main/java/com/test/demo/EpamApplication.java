package com.test.demo;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableSwagger2Doc
@EnableRetry
public class EpamApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpamApplication.class, args);
    }

}
