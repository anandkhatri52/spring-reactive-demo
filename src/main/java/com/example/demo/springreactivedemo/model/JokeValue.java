package com.example.demo.springreactivedemo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JokeValue {

    private int id;
    private String joke;
    private String[] categories;
}
