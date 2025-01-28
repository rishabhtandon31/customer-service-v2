package org.example.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerRequest {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
