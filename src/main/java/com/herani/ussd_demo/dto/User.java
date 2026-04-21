package com.herani.ussd_demo.dto;

import lombok.Data;

@Data
public class User {
    private String id;
    private String userCode;
    private String username;
    private String email;
    private String phoneNumber;
    private String bankCustomerNumber;
    private String createdAt;
    private String updatedAt;
    private boolean isCustomerLinked;
    private Object profile;
    private Object accounts;
}
