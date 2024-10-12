// ProductController.java
package io.harness.trainingapp.controller;

import io.harness.trainingapp.model.Product;
import io.harness.trainingapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId) {
        Optional<Product> product = productService.getProductById(productId);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable String productId, @RequestBody Product updatedProduct) {
        boolean isUpdated = productService.updateProduct(productId, updatedProduct);
        if (isUpdated) {
            return ResponseEntity.ok("Product updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        boolean isDeleted = productService.deleteProduct(productId);
        if (isDeleted) {
            return ResponseEntity.ok("Product deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

    @PostMapping("/cart/{productId}")
    public ResponseEntity<String> addToCart(@PathVariable String productId) {
        Optional<Product> product = productService.getProductById(productId);
        if (product.isPresent()) {
            productService.addToCart(product.get());
            return ResponseEntity.ok("Product added to cart");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

    @GetMapping("/cart")
    public List<Product> listCart() {
        return productService.listCart();
    }

    @DeleteMapping("/cart/{productId}")
    public ResponseEntity<String> removeFromCart(@PathVariable String productId) {
        boolean isRemoved = productService.removeFromCart(productId);
        if (isRemoved) {
            return ResponseEntity.ok("Product removed from cart");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found in cart");
        }
    }

    @DeleteMapping("/cart")
    public ResponseEntity<String> clearCart() {
        productService.clearCart();
        return ResponseEntity.ok("Cart cleared");
    }

    @PostMapping("/cart/pay")
    public ResponseEntity<String> payCart() {
        if (productService.isCartEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cart is empty");
        }
        productService.clearCart();
        return ResponseEntity.ok("Payment successful");
    }
}
