package com.samsung.fakestore.bean;

import com.samsung.fakestore.dto.*;
import com.samsung.fakestore.service.*;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.io.Serializable;
import java.util.*;

@Component
@RequestScope
public class OrderViewBean implements Serializable {
    @Autowired
    private CartService cartService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProductService productService;

    private List<OrderDTO> orders;

    @PostConstruct
    public void init() {
        List<CartDTO> carts = cartService.fetchAllCarts();
        Map<Long, ClientDTO> clients = new HashMap<>();
        for (ClientDTO c : clientService.fetchClients()) {
            clients.put(c.getId(), c);
        }
        Map<Long, ProductDTO> products = new HashMap<>();
        for (ProductDTO p : productService.fetchProducts()) {
            products.put(p.getId(), p);
        }

        orders = new ArrayList<>();
        for (CartDTO cart : carts) {
            ClientDTO client = clients.get(cart.getUserId());
            List<OrderProductDTO> orderProducts = new ArrayList<>();
            double total = 0.0;
            for (CartDTO.CartProductDTO pic : cart.getProducts()) {
                ProductDTO prod = products.get(pic.getProductId());
                OrderProductDTO op = new OrderProductDTO();
                op.setTitle(prod.getTitle());
                op.setCategory(prod.getCategory());
                op.setImage(prod.getImage());
                op.setPrice(prod.getPrice());
                op.setQuantity(pic.getQuantity());
                op.setSubtotal(prod.getPrice() * pic.getQuantity());
                orderProducts.add(op);
                total += op.getSubtotal();
            }
            OrderDTO order = new OrderDTO();
            order.setNumber(String.format("%06d", cart.getId()));
            order.setDate(java.time.LocalDate.parse(cart.getDate()));
            order.setClientName(client.getUsername());
            order.setProducts(orderProducts);
            order.setTotal(total);
            orders.add(order);
        }
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }
}