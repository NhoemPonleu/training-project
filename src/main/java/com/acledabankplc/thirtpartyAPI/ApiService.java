package com.acledabankplc.thirtpartyAPI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiService {

    private final RestTemplate restTemplate;
    private final WebClient webClient;
    @Value("${api.fakestore.url}")
    private String apiUrl;
    public ApiService(RestTemplate restTemplate, WebClient.Builder webClientBuilder) {
        this.restTemplate = restTemplate;
        this.webClient = webClientBuilder.baseUrl("https://fakestoreapi.com").build(); // API 2 base URL
    }

    public String callApi2WithRestTemplate() {
       // String api2Url = "https://fakestoreapi.com/products";
        return restTemplate.getForObject(apiUrl, String.class);
    }
    public Mono<String> callApi2WithWebClient() {
        return webClient.get()
                .uri("/products") // API 2 endpoint
                .retrieve()
                .bodyToMono(String.class);
    }
}
