package com.example.demo.springreactivedemo.services;

import com.example.demo.springreactivedemo.model.JokeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class JokeService {

    private RestTemplate restTemplate;
    private WebClient client = WebClient.create("http://api.icndb.com");

    @Autowired
    public JokeService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public String getJokeSync(String first, String last){
        String baseUrl = "http://api.icndb.com/jokes/random?limitTo=[nerdy]";
        String url = String.format("%s&firstName=%s&lastName=%s", baseUrl, first, last);
        return restTemplate.getForObject(url, JokeResponse.class).getValue().getJoke();
    }

    public Mono<String> getJokeAsync(String first, String last) {
        String path = "/jokes/random?limitTo=[nerdy]&firstName={first}&lastName={last}";
        return client.get()
                .uri(path, first, last)
                .retrieve()
                .bodyToMono(JokeResponse.class)
                .map(jokeResponse -> jokeResponse.getValue().getJoke());
    }

}
