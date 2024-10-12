package io.harness.trainingapp.service;

import io.harness.trainingapp.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    public void setup() throws InterruptedException {
        // Initialize ProductService and simulate setup time
        productService = new ProductService();
        Thread.sleep(1000);
    }

    @Test
    public void testListProducts() throws InterruptedException {
        Thread.sleep(1000); // Simulate test execution time
        List<Product> products = productService.listProducts();
        assertEquals(2, products.size());
    }

    @Test
    public void testGetProductById() throws InterruptedException {
        Thread.sleep(1000); // Simulate test execution time
        Optional<Product> product = productService.getProductById("1");
        assertTrue(product.isPresent());
        assertEquals("Product A", product.get().getName());
    }

    @Test
    public void testAddToCart() throws InterruptedException {
        Thread.sleep(1000); // Simulate test execution time
        Product product = productService.getProductById("1").orElseThrow();
        productService.addToCart(product);
        assertFalse(productService.isCartEmpty());
    }

    @Test
    public void testListCart() throws InterruptedException {
        Thread.sleep(1000); // Simulate test execution time
        Product product = productService.getProductById("1").orElseThrow();
        productService.addToCart(product);

        Thread.sleep(1000); // Simulate test execution time
        List<Product> cart = productService.listCart();
        assertEquals(1, cart.size());
        assertEquals("Product A", cart.get(0).getName());
    }

    @Test
    public void testClearCart() throws InterruptedException {
        Thread.sleep(1000); // Simulate test execution time
        Product product = productService.getProductById("1").orElseThrow();
        productService.addToCart(product);
        productService.clearCart();
        assertTrue(productService.isCartEmpty());
    }

    @Test
    public void testIsCartEmpty() throws InterruptedException {
        Thread.sleep(1000); // Simulate test execution time
        assertTrue(productService.isCartEmpty());
    }

    @Test
    public void testRemoveFromCart() throws InterruptedException {
        Thread.sleep(1000); // Simulate test execution time
        Product product = productService.getProductById("1").orElseThrow();
        productService.addToCart(product);

        Thread.sleep(1000); // Simulate test execution time
        boolean isRemoved = productService.removeFromCart(product.getId());
        assertTrue(isRemoved);
        assertTrue(productService.isCartEmpty());
    }

    @Test
    public void testRemoveNonExistentProductFromCart() throws InterruptedException {
        Thread.sleep(1000); // Simulate test execution time
        boolean isRemoved = productService.removeFromCart("999");
        assertFalse(isRemoved);
    }

    @Test
    public void testUpdateProduct() throws InterruptedException {
        Thread.sleep(1000); // Simulate test execution time
        Product updatedProduct = new Product("1", "Updated Product A", 15.0, "http://example.com/images/productA_updated.jpg");
        boolean isUpdated = productService.updateProduct("1", updatedProduct);
        assertTrue(isUpdated);

        Optional<Product> product = productService.getProductById("1");
        assertTrue(product.isPresent());
        assertEquals("Updated Product A", product.get().getName());
        assertEquals(15.0, product.get().getPrice());
    }

    @Test
    public void testDeleteProduct() throws InterruptedException {
        Thread.sleep(1000); // Simulate test execution time
        boolean isDeleted = productService.deleteProduct("1");
        assertTrue(isDeleted);

        Optional<Product> product = productService.getProductById("1");
        assertFalse(product.isPresent());
    }

    @Test
    public void testDeleteNonExistentProduct() throws InterruptedException {
        Thread.sleep(999); // Simulate test execution time
        boolean isDeleted = productService.deleteProduct("888");
        assertFalse(isDeleted);
    }
}
