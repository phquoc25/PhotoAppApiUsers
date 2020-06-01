package com.qph.springmicorservices.photoapp.api.users.ui.controllers;

import com.qph.springmicorservices.photoapp.api.users.ui.model.UserRequestModel;
import com.qph.springmicorservices.photoapp.api.users.ui.model.UserResponseModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final Environment env;
    private final ModelMapper modelMapper;

    @GetMapping("/status/check")
    public String getStatus()
    {
        return "working at port " + env.getProperty("local.server.port");
    }

    @PostMapping
    public ResponseEntity<UserResponseModel> createUser(@Valid @RequestBody UserRequestModel userDetail)
    {
        UserResponseModel userResponseModel = modelMapper.map(userDetail, UserResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseModel);
    }
}
