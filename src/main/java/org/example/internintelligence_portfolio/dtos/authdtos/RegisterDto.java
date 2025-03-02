package org.example.internintelligence_portfolio.dtos.authdtos;

import lombok.Data;

@Data
public class RegisterDto {
    private String email;
    private String firstname;
    private String lastname;
    private String password;
}
