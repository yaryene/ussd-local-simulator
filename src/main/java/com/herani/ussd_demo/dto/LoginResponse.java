package com.herani.ussd_demo.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private boolean success;
    private String message;
    private LoginData data;
    private int statusCode;
    private Object errors;
}
