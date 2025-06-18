package com.samsung.fakestore.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class OrderDTO implements Serializable {
    private String number;
    private LocalDate date;
    private String clientName;
    private Double total;
    private List<OrderProductDTO> products;

    // Getters e setters
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<OrderProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductDTO> products) {
        this.products = products;
    }
}