package com.example.ProfitabilityEnahancement.services;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Service;
@Service("pass")
public class GenerateSecurePassword {
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz@!#$%^&*";
    public String generatePassword() 
    {
    	int length=10;
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }
}