package com.capstone.ecommerce.order.client;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ProductClient {
    private final RestClient restClient;

    public ProductClient(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("http://product-service").build();
    }

    public void verifyProductExists(Long productId) {
        try {
            restClient.get().uri("/api/products/{id}", productId).retrieve().toBodilessEntity();
        } catch (RestClientException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product is unavailable or does not exist: " + productId, exception);
        }
    }
}
