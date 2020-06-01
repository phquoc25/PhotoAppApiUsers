package com.qph.springmicorservices.photoapp.api.users.ui.model;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponseModel {
    private final String message;
    private final Date timestamp;
}
