package com.samsung.fakestore.service;

import com.samsung.fakestore.dto.CartDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderApiService {
    private static final String API_URL = "http://localhost:8081/api/orders";

    public void createOrder(CartDTO cart) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(API_URL, cart, Void.class);
    }
}