package org.example.controller;

import org.example.dto.CustomerRequest;
import org.example.dto.CustomerResponse;
import org.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.addCustomer(customerRequest);
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long id){
        CustomerResponse customerResponse = customerService.getCustomerById(id);
        if(customerResponse != null)
            return ResponseEntity.ok(customerResponse);
        else return ResponseEntity.notFound().build();
    }
}
