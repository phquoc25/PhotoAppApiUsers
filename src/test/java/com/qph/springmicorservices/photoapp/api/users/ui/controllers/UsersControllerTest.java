package com.qph.springmicorservices.photoapp.api.users.ui.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qph.springmicorservices.photoapp.api.users.service.UserService;
import com.qph.springmicorservices.photoapp.api.users.shared.UserDto;
import com.qph.springmicorservices.photoapp.api.users.ui.model.UserRequestModel;
import com.qph.springmicorservices.photoapp.api.users.ui.model.UserResponseModel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Disabled
class UsersControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @MockBean
    private UserService userService;

    @Test
    void checkStatus_should_return_statusOK() throws Exception {
        // WHEN
        mockMvc.perform(get("/users/status/check")
                .with(remoteAddr("192.168.1.5")))
        // THEN
                .andExpect(status().isOk());
    }

    @Test
    void createUser_with_emptyFirstName_should_return_status400() throws Exception {
        // WHEN
        mockMvc.perform(post("/users")
                .with(remoteAddr("192.168.1.5"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"password\":\"toto\"}"))
        // THEN
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("First name must not be null")));
    }

    @Test
    void createUser_with_validData_should_return_status201() throws Exception {
        // GIVEN
        String firstName = "my first name";
        String lastName = "my last name";
        String email = "abc@gmail.com";
        String password = "the password";
        UserRequestModel userRequestModel = new UserRequestModel()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password);

        UserDto userDto = modelMapper.map(userRequestModel, UserDto.class);
        String encryptedPass = "encrypted password";
        String userId = "uid";
        UserDto createdUserDto = new UserDto()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setEncryptedPassword(encryptedPass)
                .setUserId(userId);
        UserResponseModel userResponseModel = modelMapper.map(createdUserDto, UserResponseModel.class);
        // WHEN
        given(userService.createUser(userDto)).willReturn(createdUserDto);
        mockMvc.perform(post("/users")
                .with(remoteAddr("192.168.1.5"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestModel)))
        // THEN
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString(objectMapper.writeValueAsString(userResponseModel))));
    }

    private static RequestPostProcessor remoteAddr(final String remoteAddr) { // it's nice to extract into a helper
        return request -> {
            request.setRemoteAddr(remoteAddr);
            return request;
        };
    }
}