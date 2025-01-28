package org.example.service;

import org.example.dto.CustomerRequest;
import org.example.dto.CustomerResponse;

public interface CustomerService {
    CustomerResponse addCustomer(CustomerRequest customerRequest);
    CustomerResponse getCustomerById(Long id);
}
