package com.herani.ussd_demo.service;

import com.herani.ussd_demo.dto.LoginRequest;
import com.herani.ussd_demo.dto.LoginResponse;
import com.herani.ussd_demo.dto.LoginData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {
    
    @Value("${auth.api.url:https://api.shabelle.shega.heranitech.com/api/User/login}")
    private String loginApiUrl;
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    // Store session tokens (in a real app, use Redis or proper session management)
    private final Map<String, String> sessionTokens = new ConcurrentHashMap<>();
    public LoginResponse authenticateUser(String pin, String sessionId) {
        // Basic validation for USSD PIN (must be exactly 6 digits)
        if (pin == null || pin.length() != 6 || !pin.matches("\\d{6}")) {
            LoginResponse errorResponse = new LoginResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Invalid PIN format");
            errorResponse.setStatusCode(400);
            return errorResponse;
        }

        try {
            // Create login request with device info
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setPin(pin);
            loginRequest.setDeviceUuid("b0bf69d5-f2ba-4699-b810-00c9ca3a32e7");
            loginRequest.setDevicePlatform("android");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<LoginRequest> request = new HttpEntity<>(loginRequest, headers);

            ResponseEntity<LoginResponse> response = restTemplate.postForEntity(
                loginApiUrl, request, LoginResponse.class);

            LoginResponse loginResponse = response.getBody();
            
            // Store token for session if login successful
            if (loginResponse != null && loginResponse.isSuccess() && 
                loginResponse.getData() != null && loginResponse.getData().getAccessToken() != null) {
                sessionTokens.put(sessionId, loginResponse.getData().getAccessToken());
            }

            return loginResponse;
        } catch (Exception e) {
            LoginResponse errorResponse = new LoginResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Login failed: " + e.getMessage());
            errorResponse.setStatusCode(500);
            return errorResponse;
        }
    }

    public String getAccessToken(String sessionId) {
        return sessionTokens.get(sessionId);
    }

    public boolean isAuthenticated(String sessionId) {
        return sessionTokens.containsKey(sessionId);
    }

    public void logout(String sessionId) {
        sessionTokens.remove(sessionId);
    }
}
