package com.samsung.fakestore.bean;

import com.samsung.fakestore.dto.CartDTO;
import com.samsung.fakestore.dto.ClientDTO;
import com.samsung.fakestore.dto.ProductDTO;
import com.samsung.fakestore.service.CartService;
import com.samsung.fakestore.service.ClientService;
import com.samsung.fakestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import jakarta.annotation.PostConstruct;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequestScope
public class SearchBean implements Serializable {

    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ClientService clientService;

    // Filtros
    private Long selectedClient;
    private LocalDate orderDateStart;
    private LocalDate orderDateEnd;
    private String orderNumber;

    // Dados para o select de clientes
    private List<ClientDTO> clients;

    // Resultado da busca
    private List<CartDTO> carts;

    @PostConstruct
    public void init() {
        clients = clientService.fetchClients();
        carts = new ArrayList<>();
    }

    public void search() {
        List<CartDTO> allCarts = cartService.fetchAllCarts();
        List<ProductDTO> allProducts = productService.fetchProducts();

        carts = allCarts.stream()
                .filter(cart -> selectedClient == null || cart.getUserId().equals(selectedClient))
                .filter(cart -> orderNumber == null || orderNumber.isBlank()
                        || String.valueOf(cart.getId()).equals(orderNumber))
                .filter(cart -> {
                    if (orderDateStart == null && orderDateEnd == null)
                        return true;
                    try {
                        LocalDate cartDate = LocalDate.parse(cart.getDate().substring(0, 10));
                        boolean afterStart = orderDateStart == null || !cartDate.isBefore(orderDateStart);
                        boolean beforeEnd = orderDateEnd == null || !cartDate.isAfter(orderDateEnd);
                        return afterStart && beforeEnd;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .peek(cart -> {
                    // Para cada produto do cart, busque detalhes e monte a lista detalhada
                    List<CartDTO.CartProductDTO> detailedProducts = new ArrayList<>();
                    for (CartDTO.CartProductDTO pic : cart.getProducts()) {
                        ProductDTO prod = allProducts.stream()
                                .filter(p -> p.getId().equals(pic.getProductId()))
                                .findFirst().orElse(null);
                        if (prod != null) {
                            CartDTO.CartProductDTO cp = new CartDTO.CartProductDTO();
                            cp.setProduct(prod);
                            cp.setQuantity(pic.getQuantity());
                            detailedProducts.add(cp);
                        }
                    }
                    cart.setProducts(detailedProducts);
                })
                .collect(Collectors.toList());
    }

    public void reset() {
        selectedClient = null;
        orderDateStart = null;
        orderDateEnd = null;
        orderNumber = null;
        carts = new ArrayList<>();
    }

    public String getFormattedDate(CartDTO cart) {
        try {
            LocalDate localDate = LocalDate.parse(cart.getDate().substring(0, 10));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return localDate.format(formatter);
        } catch (Exception e) {
            return cart.getDate();
        }
    }

    public String getClientNameById(Long userId) {
        return clients.stream()
                .filter(c -> c.getId().equals(userId))
                .map(c -> c.getUsername())
                .findFirst().orElse("N/A");
    }

    public double getCartTotal(CartDTO cart) {
        return cart.getProducts().stream()
                .mapToDouble(cp -> cp.getProduct().getPrice() * cp.getQuantity())
                .sum();
    }

    public double getSubtotal(CartDTO.CartProductDTO cp) {
        return cp.getProduct().getPrice() * cp.getQuantity();
    }

    public Long getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Long selectedClient) {
        this.selectedClient = selectedClient;
    }

    public LocalDate getOrderDateStart() {
        return orderDateStart;
    }

    public void setOrderDateStart(LocalDate orderDateStart) {
        this.orderDateStart = orderDateStart;
    }

    public LocalDate getOrderDateEnd() {
        return orderDateEnd;
    }

    public void setOrderDateEnd(LocalDate orderDateEnd) {
        this.orderDateEnd = orderDateEnd;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<ClientDTO> getClients() {
        return clients;
    }

    public void setClients(List<ClientDTO> clients) {
        this.clients = clients;
    }

    public List<CartDTO> getCarts() {
        return carts;
    }

    public void setCarts(List<CartDTO> carts) {
        this.carts = carts;
    }
}