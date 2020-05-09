package com.example.demo.springreactivedemo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JokeResponse {

    private String type;
    private JokeValue value;
}
