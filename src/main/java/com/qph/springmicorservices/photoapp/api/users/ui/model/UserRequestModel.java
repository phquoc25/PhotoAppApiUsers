package com.qph.springmicorservices.photoapp.api.users.ui.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
@Accessors(chain = true)
public class UserRequestModel implements Serializable {
    private static final long serialVersionUID = 536260459766273973L;

    @NotNull(message = "First name must not be null")
    @Size(min = 1, message = "First name must not be empty")
    private String firstName;
    @NotNull(message = "Last name must not be null")
    @Size(min = 1, message = "Last name must not be empty")
    private String lastName;
    @Email(message = "email is not correct")
    private String email;
    @NotNull(message = "password must not be null")
    @Size(min = 8, message = "password has to be at least 8 characters")
    private String password;
}
