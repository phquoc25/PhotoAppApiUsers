package com.qph.springmicorservices.photoapp.api.users.ui.controllers;

import com.qph.springmicorservices.photoapp.api.users.ui.model.UserRequestModel;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final Environment env;

    public UsersController(Environment env) {
        this.env = env;
    }

    @GetMapping("/status/check")
    public String getStatus()
    {
        return "working at port " + env.getProperty("local.server.port");
    }

    @PostMapping
    public String createUser(@Valid @RequestBody UserRequestModel userDetail)
    {
        return "create user method called";
    }
}
