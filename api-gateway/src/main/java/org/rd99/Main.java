package org.rd99;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}