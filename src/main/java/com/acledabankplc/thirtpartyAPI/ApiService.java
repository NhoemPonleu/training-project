package com.acledabankplc.thirtpartyAPI;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiService {

    private final RestTemplate restTemplate;
    private final WebClient webClient;

    // Constructor injection of RestTemplate and WebClient
    public ApiService(RestTemplate restTemplate, WebClient.Builder webClientBuilder) {
        this.restTemplate = restTemplate;
        this.webClient = webClientBuilder.baseUrl("https://fakestoreapi.com").build(); // API 2 base URL
    }

    // Fetch data from API 2 using RestTemplate
    public String callApi2WithRestTemplate() {
        String api2Url = "https://fakestoreapi.com/products"; // API 2 endpoint
        return restTemplate.getForObject(api2Url, String.class);
    }

    // Fetch data from API 2 using WebClient
    public Mono<String> callApi2WithWebClient() {
        return webClient.get()
                .uri("/products") // API 2 endpoint
                .retrieve()
                .bodyToMono(String.class);
    }
}
