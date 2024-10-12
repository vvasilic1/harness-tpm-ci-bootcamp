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

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean updateProduct(String productId, Product updatedProduct) {
        Optional<Product> existingProduct = getProductById(productId);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            return true;
        }
        return false;
    }

    public boolean deleteProduct(String productId) {
        return products.removeIf(product -> product.getId().equals(productId));
    }

    public void addToCart(Product product) {
    if (product != null) {
        System.out.println("Adding product to cart: " + product.getId() + " - " + product.getName());
        cart.put(product.getId(), product);
    } else {
        System.out.println("Attempted to add null product to cart.");
    }
}


    public List<Product> listCart() {
        return new ArrayList<>(cart.values());
    }

    public boolean removeFromCart(String productId) {
        return cart.remove(productId) != null;
    }

    public void clearCart() {
        cart.clear();
    }

    public boolean isCartEmpty() {
        return cart.isEmpty();
    }
}
