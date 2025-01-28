package org.example.service.impl;

import org.example.dto.Customer;
import org.example.dto.CustomerRequest;
import org.example.dto.CustomerResponse;
import org.example.repository.CustomerRepository;
import org.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        //Validation Checks


        Customer customer = new Customer();
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setDateOfBirth(customerRequest.getDateOfBirth());
        Customer savedCustomer = customerRepository.save(customer);

        //generating the response
        CustomerResponse customerResponse = CustomerResponse.builder()
                .id(savedCustomer.getId())
                .firstName(savedCustomer.getFirstName())
                .lastName(savedCustomer.getLastName())
                .dateOfBirth(savedCustomer.getDateOfBirth())
                .build();
        return customerResponse;
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            CustomerResponse customerResponse = CustomerResponse.builder()
                    .id(customer.getId())
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .dateOfBirth(customer.getDateOfBirth())
                    .build();
            return customerResponse;
        }
        return null;
    }
}
