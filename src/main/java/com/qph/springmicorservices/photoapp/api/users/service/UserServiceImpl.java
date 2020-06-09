package com.qph.springmicorservices.photoapp.api.users.service;

import com.qph.springmicorservices.photoapp.api.users.data.UserRepository;
import com.qph.springmicorservices.photoapp.api.users.data.entity.UserEntity;
import com.qph.springmicorservices.photoapp.api.users.shared.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final IdGenerator idGenerator;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("Create user {}", userDto);
        userDto.setEncryptedPassword("encrypted pass");
        userDto.setUserId(idGenerator.generateId());

        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        UserEntity createdUser = userRepository.save(userEntity);

        return modelMapper.map(createdUser, UserDto.class);
    }
}
