package com.qph.springmicorservices.photoapp.api.users.ui.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Setter
@Getter
@Accessors(chain = true)
public class UserResponseModel implements Serializable {
    private static final long serialVersionUID = -6462774607698239790L;

    private String firstName;
    private String lastName;
    private String email;
}
