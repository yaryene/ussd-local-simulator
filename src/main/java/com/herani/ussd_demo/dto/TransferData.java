package com.herani.ussd_demo.dto;

import lombok.Data;

@Data
public class TransferData {
    private String referenceId;
    private String applicationConfigurationId;
    private String status;
}
