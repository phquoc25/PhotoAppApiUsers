package com.qph.springmicorservices.photoapp.api.users.ui.controllers;

import com.qph.springmicorservices.photoapp.api.users.service.UserService;
import com.qph.springmicorservices.photoapp.api.users.shared.UserDto;
import com.qph.springmicorservices.photoapp.api.users.ui.model.UserRequestModel;
import com.qph.springmicorservices.photoapp.api.users.ui.model.UserResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UsersController {
    private final Environment env;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @GetMapping("/status/check")
    public String getStatus()
    {
        return "working at port " + env.getProperty("local.server.port");
    }

    @PostMapping
    public ResponseEntity<UserResponseModel> createUser(@Valid @RequestBody UserRequestModel userDetail)
    {
        log.info("Send a post request to create user {}", userDetail);
        UserDto userDto = modelMapper.map(userDetail, UserDto.class);
        UserDto createdUser = userService.createUser(userDto);
        UserResponseModel userResponseModel = modelMapper.map(createdUser, UserResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseModel);
    }
}
