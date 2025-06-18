package com.samsung.fakestore.bean;

import com.samsung.fakestore.dto.ProductDTO;
import com.samsung.fakestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Component
@RequestScope
public class ProductBean {

    @Autowired
    private ProductService productService;

    private List<ProductDTO> products;

    @PostConstruct
    public void init() {
        products = productService.fetchProducts();
    }

    public List<ProductDTO> getProducts() {
        return products;
    }
}