// ProductControllerTest.java
package io.harness.trainingapp.controller;
import org.springframework.web.bind.annotation.RestController;

import io.harness.trainingapp.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
    public void setup() {
        // Clear the cart before every test
    }

    @Test
    public void testListProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':'1','name':'Product A','price':10.0},{'id':'2','name':'Product B','price':20.0}]"));
    }

    @Test
    public void testAddToCart() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/products/cart/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Product added to cart"));
    }

    @Test
    public void testListCart() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/products/cart/1"))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/products/cart"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':'1','name':'Product A','price':10.0}]"));
    }

    @Test
    public void testPayCart() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/products/cart/1"))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.post("/products/cart/pay"))
                .andExpect(status().isOk())
                .andExpect(content().string("Payment successful"));
    }
}