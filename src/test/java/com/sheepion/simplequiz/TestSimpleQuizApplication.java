package com.sheepion.simplequiz;

import org.springframework.boot.SpringApplication;

public class TestSimpleQuizApplication {
    
    public static void main(String[] args) {
        SpringApplication.from(SimpleQuizApplication::main).with(TestcontainersConfiguration.class).run(args);
    }
    
}
