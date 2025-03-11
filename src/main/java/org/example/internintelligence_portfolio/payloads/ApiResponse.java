package org.example.internintelligence_portfolio.payloads;

import lombok.Data;

import java.util.Objects;

@Data
public class ApiResponse {
    private boolean success;
    private String message;
    private String token;
    private Object data;

    public ApiResponse(boolean success,String message, String token){
        this.success = success;
        this.message = message;
        this.token = token;
    }
    public ApiResponse(boolean success,String message, Object data){
        this.success = success;
        this.message = message;
        this.data = data;
    }
    public ApiResponse(boolean success,String message){
        this.success = success;
        this.message = message;
    }

    public ApiResponse(String message){
        this.message = message;
    }

}