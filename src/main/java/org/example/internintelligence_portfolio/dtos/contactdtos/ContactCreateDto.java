package org.example.internintelligence_portfolio.dtos.contactdtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContactCreateDto {
    private String fullName;
    private String email;
    private String message;
}
