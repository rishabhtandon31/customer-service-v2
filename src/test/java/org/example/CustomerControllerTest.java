package org.example;

import org.example.controller.CustomerController;
import org.example.dto.CustomerRequest;
import org.example.dto.CustomerResponse;
import org.example.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private CustomerResponse customer;

    @BeforeEach
    void setUp() {
        customer = CustomerResponse.builder()
                .id(1L)
                .firstName("Rishabh")
                .lastName("Tandon")
                .dateOfBirth(LocalDate.of(1990, 5, 15))
                .build();
    }

    @Test
    void testAddCustomer() throws Exception {
        Mockito.when(customerService.addCustomer(Mockito.any(CustomerRequest.class))).thenReturn(customer);

        mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"Rishabh\", \"lastName\": \"Tandon\", \"dateOfBirth\": \"1990-05-15\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Rishabh"))
                .andExpect(jsonPath("$.lastName").value("Tandon"));
    }

    @Test
    void testGetCustomerById() throws Exception {
        Mockito.when(customerService.getCustomerById(1L)).thenReturn(customer);

        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Rishabh"))
                .andExpect(jsonPath("$.lastName").value("Tandon"));
    }

    @Test
    void testGetCustomerById_NotFound() throws Exception {
        Mockito.when(customerService.getCustomerById(2L)).thenReturn(null);

        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isNotFound());
    }
}
