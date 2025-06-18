package com.samsung.fakestore.service;

import com.samsung.fakestore.dto.ClientDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientService {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String USERS_URL = "http://localhost:8081/api/users";

    public List<ClientDTO> fetchClients() {
        ClientDTO[] users = restTemplate.getForObject(USERS_URL, ClientDTO[].class);
        return Arrays.asList(users);
    }
}