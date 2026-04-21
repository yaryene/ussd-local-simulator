package com.herani.ussd_demo.dto;

import lombok.Data;

@Data
public class TransferResponse {
    private boolean success;
    private String message;
    private TransferData data;
    private int statusCode;
    private Object errors;
}
