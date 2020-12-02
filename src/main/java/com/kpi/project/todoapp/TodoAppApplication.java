package com.kpi.project.todoapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(TodoAppApplication.class, args);
    }

}
