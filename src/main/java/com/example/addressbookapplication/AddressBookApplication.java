package com.example.addressbookapplication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class AddressBookApplication {
    public static void main(String[] args) {
        System.out.println("Welcome to Employee Payroll App");
        ApplicationContext context;
        context = SpringApplication.run(AddressBookApplication.class, args);
        log.info("Address Book App Started");
        log.info("Address Book App Started in "+ context.getEnvironment().getProperty("environment")+" Environment");
    }
}


