package com.qph.springmicorservices.photoapp.api.users.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserIdGeneratorImpl implements IdGenerator {
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
