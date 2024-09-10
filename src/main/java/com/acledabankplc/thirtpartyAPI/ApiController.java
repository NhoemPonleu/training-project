package com.acledabankplc.thirtpartyAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/api/v1/fetch-from-api2")
    public String fetchFromApi2() {
        // Call the service that fetches data from API 2
        return apiService.callApi2WithRestTemplate();
    }
    @GetMapping("/api/v1/fetch-from-api2/webflux")
    public Mono<String> fetchFromApi2WithWebClient() {
        return apiService.callApi2WithWebClient();
    }
}
