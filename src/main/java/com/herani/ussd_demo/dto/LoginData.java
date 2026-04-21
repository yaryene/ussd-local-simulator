package com.herani.ussd_demo.dto;

import lombok.Data;

@Data
public class LoginData {
    private User user;
    private String accessToken;
}
