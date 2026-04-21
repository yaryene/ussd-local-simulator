package com.herani.ussd_demo.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String pin;
    private String deviceUuid;
    private String devicePlatform;
}
