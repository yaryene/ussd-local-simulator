package com.herani.ussd_demo.dto;

import lombok.Data;

@Data
public class TransferRequest {
    private String fromAccountNo;
    private String toAccountNo;
    private double amount;
    private String remark;
}
