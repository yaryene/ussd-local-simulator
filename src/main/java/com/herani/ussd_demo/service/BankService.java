package com.herani.ussd_demo.service;

import com.herani.ussd_demo.dto.TransferRequest;
import com.herani.ussd_demo.dto.TransferResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BankService {
    
    @Value("${bank.api.url:https://api.shabelle.shega.heranitech.com/api/Transfer/internal}")
    private String bankApiUrl;
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    public TransferResponse performInternalTransfer(String accessToken, TransferRequest transferRequest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + accessToken);
            
            HttpEntity<TransferRequest> request = new HttpEntity<>(transferRequest, headers);
            
            ResponseEntity<TransferResponse> response = restTemplate.postForEntity(
                bankApiUrl, request, TransferResponse.class);
            
            return response.getBody();
        } catch (Exception e) {
            TransferResponse errorResponse = new TransferResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Transfer failed: " + e.getMessage());
            errorResponse.setStatusCode(500);
            return errorResponse;
        }
    }
}
