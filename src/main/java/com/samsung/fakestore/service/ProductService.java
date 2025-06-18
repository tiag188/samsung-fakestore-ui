package com.samsung.fakestore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsung.fakestore.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    public List<ProductDTO> fetchProducts() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8081/api/products"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            ProductDTO[] products = mapper.readValue(response.body(), ProductDTO[].class);

            return Arrays.asList(products);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}