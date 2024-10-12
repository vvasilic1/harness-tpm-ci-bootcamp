// ProductController.java
package io.harness.trainingapp.controller;
import org.springframework.web.bind.annotation.RestController;
import io.harness.trainingapp.model.Product;
import io.harness.trainingapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> listProducts() {
        return productService.listProducts();
    }

    @PostMapping("/cart/{productId}")
    public String addToCart(@PathVariable String productId) {
        productService.getProductById(productId)
                .ifPresent(productService::addToCart);
        return "Product added to cart";
    }

    @GetMapping("/cart")
    public List<Product> listCart() {
        return productService.listCart();
    }

    @PostMapping("/cart/pay")
    public String payCart() {
        if (productService.isCartEmpty()) {
            return "Cart is empty";
        }
        productService.clearCart();
        return "Payment successful";
    }
}