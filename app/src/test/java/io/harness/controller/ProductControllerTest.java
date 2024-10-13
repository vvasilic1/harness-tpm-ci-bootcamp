// ProductControllerTest.java
package io.harness.trainingapp.controller;

import io.harness.trainingapp.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws InterruptedException {
        // Simulate setup time
        Thread.sleep(1000);
    }

    @Test
    public void testListProducts() throws Exception {
        Thread.sleep(1000); // Simulate test execution time
        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetProductById() throws Exception {
        Thread.sleep(1000); // Simulate test execution time
        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateProduct() throws Exception {
        Thread.sleep(1000); // Simulate test execution time
        String newProduct = "{\"id\":\"3\",\"name\":\"Product C\",\"price\":30.0}";
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newProduct))
                .andExpect(status().isCreated())
                .andExpect(content().string("Product created successfully"));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Thread.sleep(1000); // Simulate test execution time
        String updatedProduct = "{\"name\":\"Updated Product A\",\"price\":15.0}";
        mockMvc.perform(MockMvcRequestBuilders.put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedProduct))
                .andExpect(status().isOk())
                .andExpect(content().string("Product updated successfully"));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Thread.sleep(1000); // Simulate test execution time
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Product deleted successfully"));
    }

    @Test
    public void testAddToCart() throws Exception {
        Thread.sleep(1000); // Simulate test execution time
        mockMvc.perform(MockMvcRequestBuilders.post("/products/cart/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Product added to cart"));
    }

    @Test
    public void testListCart() throws Exception {
        // Step 1: Create a product first to ensure it exists before adding to cart
        Thread.sleep(1000); // Simulate test execution time
        String newProduct = "{\"id\":\"1\",\"name\":\"Product A\",\"price\":10.0,\"imageUrl\":\"http://example.com/images/productA.jpg\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newProduct))
                .andExpect(status().isCreated())
                .andExpect(content().string("Product created successfully"));

        // Step 2: Add the product to the cart
        Thread.sleep(1000); // Simulate test execution time
        mockMvc.perform(MockMvcRequestBuilders.post("/products/cart/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Product added to cart"));

        // Step 3: Get cart contents and store response for debugging
        Thread.sleep(1000); // Simulate test execution time
        String responseContent = mockMvc.perform(MockMvcRequestBuilders.get("/products/cart"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Print the response content for debugging
        System.out.println("Cart Response Content: " + responseContent);

        // Step 4: Validate the cart content against the expected JSON
        mockMvc.perform(MockMvcRequestBuilders.get("/products/cart"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"1\",\"name\":\"Product A\",\"price\":10.0,\"imageUrl\":\"http://example.com/images/productA.jpg\"}]"));
    }


    @Test
    public void testRemoveFromCart() throws Exception {
        Thread.sleep(1000); // Simulate test execution time
        mockMvc.perform(MockMvcRequestBuilders.post("/products/cart/1"))
                .andExpect(status().isOk());

        Thread.sleep(1000); // Simulate test execution time
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/cart/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Product removed from cart"));
    }

    @Test
    public void testClearCart() throws Exception {
        Thread.sleep(1000); // Simulate test execution time
        mockMvc.perform(MockMvcRequestBuilders.post("/products/cart/1"))
                .andExpect(status().isOk());

        Thread.sleep(1000); // Simulate test execution time
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/cart"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cart cleared"));
    }

    @Test
    public void testPayCart() throws Exception {
        Thread.sleep(1000); // Simulate test execution time
        mockMvc.perform(MockMvcRequestBuilders.post("/products/cart/1"))
                .andExpect(status().isOk());

        Thread.sleep(1000); // Simulate test execution time
        mockMvc.perform(MockMvcRequestBuilders.post("/products/cart/pay"))
                .andExpect(status().isOk())
                .andExpect(content().string("Payment successful"));
    }

    @Test
    public void testPayEmptyCart() throws Exception {
        Thread.sleep(1000); // Simulate test execution time
        mockMvc.perform(MockMvcRequestBuilders.post("/products/cart/pay"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Cart is empty"));
    }
}
