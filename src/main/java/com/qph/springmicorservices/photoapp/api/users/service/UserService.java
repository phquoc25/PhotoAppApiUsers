package com.qph.springmicorservices.photoapp.api.users.service;

import com.qph.springmicorservices.photoapp.api.users.shared.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
}
