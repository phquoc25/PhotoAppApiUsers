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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserIdGeneratorImpl userIdGenerator;

    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        userService = new UserServiceImpl(userIdGenerator, userRepository, modelMapper);
    }

    @Test
    void createUser()
    {
        // GIVEN
        String firstName = "first name";
        String lastName = "last name";
        String email = "email@email.com";
        UserDto userDto = new UserDto()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email);

        String userId = "userid-sfdsf";
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setUserId(userId);
        userEntity.setEncryptedPassword("encrypted pass");

        UserEntity createdUserEntity = new UserEntity();
        modelMapper.map(userEntity, createdUserEntity);
        Integer id = 12345;
        createdUserEntity.setId(id);

        given(userIdGenerator.generateId()).willReturn(userId);
        given(userRepository.save(userEntity)).willReturn(createdUserEntity);
        // WHEN
        UserDto createdUser = userService.createUser(userDto);
        // THEN
        then(userIdGenerator).should().generateId();
        then(userRepository).should().save(userEntity);
        assertThat(createdUser).isEqualToComparingFieldByField(userDto);
        assertThat(createdUser.getUserId()).isEqualTo(userId);
        assertThat(createdUser.getEncryptedPassword()).isEqualTo("encrypted pass");
    }
}