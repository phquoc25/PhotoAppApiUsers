package com.qph.springmicorservices.photoapp.api.users.data;

import com.qph.springmicorservices.photoapp.api.users.data.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @BeforeEach
    void setUp() {
    }

    @Test
    void saveUser_with_validUser_should_return_createdUser() {
        // GIVEN
        String userID = "uuic-abcd-asde";
        String firstName = "user name";
        String lastName = "last name";
        String email = "email@gmail.com";
        String password = "encryptedPassword";
        UserEntity user = new UserEntity()
                .setUserId(userID)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setEncryptedPassword(password);

        // WHEN
        UserEntity createdUser = userRepository.save(user);
        // THEN
        assertThat(createdUser).isEqualToComparingFieldByField(user);
        assertThat(createdUser.getId()).isNotNull();
    }
}