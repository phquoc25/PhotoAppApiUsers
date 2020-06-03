package com.qph.springmicorservices.photoapp.api.users.service;

import com.qph.springmicorservices.photoapp.api.users.shared.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final IdGenerator idGenerator;
    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("Create user {}", userDto);
        userDto.setEncryptedPassword("encrypted pass");
        userDto.setUserId(idGenerator.generateId());
        return userDto;
    }
}
