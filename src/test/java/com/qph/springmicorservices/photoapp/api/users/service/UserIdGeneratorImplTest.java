package com.qph.springmicorservices.photoapp.api.users.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserIdGeneratorImplTest {
    private UserIdGeneratorImpl userIdGenerator;
    @BeforeEach
    void setUp()
    {
        userIdGenerator = new UserIdGeneratorImpl();
    }

    @Test
    void generateId_with_2Call_should_return_differentIds()
    {
        // GIVEN
        // WHEN
        String id1 = userIdGenerator.generateId();
        String id2 = userIdGenerator.generateId();

        // THEN
        assertThat(id1).isNotEqualTo(id2);
    }
}