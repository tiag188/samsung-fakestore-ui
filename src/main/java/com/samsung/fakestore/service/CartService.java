package com.samsung.fakestore.service;

import com.samsung.fakestore.dto.CartDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CartService {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String CARTS_URL = "http://localhost:8081/api/carts";

    public List<CartDTO> fetchAllCarts() {
        CartDTO[] carts = restTemplate.getForObject(CARTS_URL, CartDTO[].class);
        return Arrays.asList(carts);
    }
}