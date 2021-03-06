package com.qph.springmicorservices.photoapp.api.users.service;

import com.qph.springmicorservices.photoapp.api.users.data.UserRepository;
import com.qph.springmicorservices.photoapp.api.users.data.entity.UserEntity;
import com.qph.springmicorservices.photoapp.api.users.shared.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    private static final String ENCRYPTED_PASS = "encrypted pass";
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserIdGeneratorImpl userIdGenerator;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        userService = new UserServiceImpl(userIdGenerator, bCryptPasswordEncoder, userRepository, modelMapper);
    }

    @Test
    void createUser()
    {
        // GIVEN
        String firstName = "first name";
        String lastName = "last name";
        String email = "email@email.com";
        String password = "password";
        UserDto userDto = new UserDto()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPassword(password)
                .setEmail(email);

        String userId = "userid-sfdsf";
        UserEntity userEntity = new UserEntity()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setUserId(userId)
                .setEncryptedPassword(ENCRYPTED_PASS);

        UserEntity createdUserEntity = new UserEntity();
        modelMapper.map(userEntity, createdUserEntity);
        Integer id = 12345;
        createdUserEntity.setId(id);

        given(userIdGenerator.generateId()).willReturn(userId);
        given(bCryptPasswordEncoder.encode(password)).willReturn(ENCRYPTED_PASS);
        given(userRepository.save(userEntity)).willReturn(createdUserEntity);
        // WHEN
        UserDto createdUser = userService.createUser(userDto);
        // THEN
        then(userIdGenerator).should().generateId();
        then(bCryptPasswordEncoder).should().encode(password);
        then(userRepository).should().save(userEntity);
        assertThat(createdUser).isEqualToIgnoringGivenFields(userDto, "password", "userId", "encryptedPassword");
        assertThat(createdUser.getUserId()).isEqualTo(userId);
        assertThat(createdUser.getEncryptedPassword()).isEqualTo(ENCRYPTED_PASS);
    }
}