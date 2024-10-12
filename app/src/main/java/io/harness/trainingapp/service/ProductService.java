// ProductService.java
package io.harness.trainingapp.service;

import io.harness.trainingapp.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();
    private final Map<String, Product> cart = new HashMap<>();

    public ProductService() {
        products.add(new Product("1", "Product A", 10.0, "http://example.com/images/productA.jpg"));
        products.add(new Product("2", "Product B", 20.0, "http://example.com/images/productB.jpg"));
    }

    public List<Product> listProducts() {
        return products;
    }

    public Optional<Product> getProductById(String productId) {
        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();
    }

    public void addToCart(Product product) {
        cart.put(product.getId(), product);
    }

    public List<Product> listCart() {
        return new ArrayList<>(cart.values());
    }

    public void clearCart() {
        cart.clear();
    }

    public boolean isCartEmpty() {
        return cart.isEmpty();
    }
}