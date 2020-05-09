package com.example.demo.springreactivedemo.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class JokeServiceTest {

    @Autowired
    private JokeService jokeService;

    @Test
    void test_getJokeSync() {
        String joke = jokeService.getJokeSync("Anand", "Khatri");
        assertTrue(joke.contains("Anand") || joke.contains("Khatri"));
    }

    @Test
    void test_getJokeAsync() {
        String joke = jokeService.getJokeAsync("Anand", "Khatri")
                .block(Duration.ofSeconds(2));
        assertTrue(joke.contains("Anand") || joke.contains("Khatri"));
    }

}
