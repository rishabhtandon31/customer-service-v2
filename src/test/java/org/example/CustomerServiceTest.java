package org.example;

import org.example.dto.Customer;
import org.example.dto.CustomerRequest;
import org.example.dto.CustomerResponse;
import org.example.repository.CustomerRepository;
import org.example.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Optional;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        customer =  new Customer();
        customer.setId(1L);
        customer.setFirstName("Rishabh");
        customer.setLastName("Tandon");
        customer.setDateOfBirth(LocalDate.of(1995, 5, 15));
    }

    @Test
    void saveCustomer(){
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setFirstName("Rishabh");
        customerRequest.setLastName("Tandon");
        customerRequest.setDateOfBirth(LocalDate.of(1995, 5, 15));

        CustomerResponse customerResponse = customerService.addCustomer(customerRequest);
        Assertions.assertNotNull(customerResponse);

        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        Mockito.verify(customerRepository).save(customerCaptor.capture());

        Customer capturedCustomer = customerCaptor.getValue();
        // Validate the captured argument values
        Assertions.assertEquals("Rishabh", capturedCustomer.getFirstName());
        Assertions.assertEquals("Tandon", capturedCustomer.getLastName());
        Assertions.assertEquals(LocalDate.of(1995, 5, 15), capturedCustomer.getDateOfBirth());
    }

    @Test
    void testGetCustomerById() {
       Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        CustomerResponse foundCustomer = customerService.getCustomerById(1L);
        Assertions.assertNotNull(foundCustomer);
        Assertions.assertEquals("Rishabh", foundCustomer.getFirstName());
    }

    @Test
    void testGetCustomerById_NotFound() {
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        CustomerResponse foundCustomer = customerService.getCustomerById(2L);
        Assertions.assertNull(foundCustomer);
    }

}
