package com.takima.backskeleton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.takima.backskeleton")
public class BackSkeletonApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackSkeletonApplication.class, args);
    }
}
