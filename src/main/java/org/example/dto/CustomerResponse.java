package org.example.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CustomerResponse {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
