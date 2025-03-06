package org.example.internintelligence_portfolio.payloads;

import lombok.Data;

@Data
public class ApiResponse {
    private boolean success;
    private String message;
    private String token;

    public ApiResponse(boolean success,String message, String token){
        this.success = success;
        this.message = message;
        this.token = token;
    }
    public ApiResponse(boolean success,String message){
        this.success = success;
        this.message = message;
    }

    public ApiResponse(String message){
        this.message = message;
    }

}