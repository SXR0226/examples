package com.example.example07.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Map;

@Component
public class EncryptorComponent {
    @Value("${my.secretkey}")
    private String secretKey;
    @Value("${my.salt}")
    private String salt;
    @Autowired
    private ObjectMapper mapper;
    public String encrypt(Map payload){
        try {
            String json=mapper.writeValueAsString(payload);
            return Encryptors.text(secretKey, salt).encrypt(json);
        } catch (JsonProcessingException e) {
        }
        return null;
    }
    public Map<String,Object> decrypt(String encryptString){
        try {
            String json=Encryptors.text(secretKey, salt).decrypt(encryptString);
            return mapper.readValue(json, Map.class);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"未登录");
        }
    }
}
