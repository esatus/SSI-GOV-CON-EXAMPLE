package com.esatus.ssi.gov.connector.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.esatus.ssi"})
public class Main {
  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }
}
