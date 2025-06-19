package com.samsung.fakestore.bean;

import com.samsung.fakestore.dto.CartDTO;
import com.samsung.fakestore.dto.ProductDTO;
import com.samsung.fakestore.service.OrderApiService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class CreateOrderBean implements Serializable {

    @Autowired
    private OrderApiService orderApiService;

    private CartDTO cart;
    private ProductDTO newProduct;

    @PostConstruct
    public void init() {
        cart = new CartDTO();
        cart.setProducts(new ArrayList<>());
        newProduct = new ProductDTO();
    }

    public void addProduct() {
        CartDTO.CartProductDTO cartProduct = new CartDTO.CartProductDTO();
        cartProduct.setProduct(newProduct);
        cartProduct.setQuantity(newProduct.getQuantity());
        cart.getProducts().add(cartProduct);
        newProduct = new ProductDTO();
    }

    public void createOrder() {
        orderApiService.createOrder(cart);
        cart = new CartDTO();
        cart.setProducts(new ArrayList<>());
    }

    // getters/setters
    public CartDTO getCart() {
        return cart;
    }

    public void setCart(CartDTO cart) {
        this.cart = cart;
    }

    public ProductDTO getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(ProductDTO newProduct) {
        this.newProduct = newProduct;
    }
}