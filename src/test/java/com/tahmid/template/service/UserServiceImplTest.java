package com.tahmid.template.service;

import com.tahmid.template.dto.UserDTO;
import com.tahmid.template.exception.ResourceNotFoundException;
import com.tahmid.template.mapper.UserMapper;
import com.tahmid.template.model.User;
import com.tahmid.template.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {

        UserDTO userDTO = new UserDTO();
        userDTO.setName("John Doe");

        var userEntity = UserMapper.toEntity(userDTO);

        when(userRepository.save(any())).thenReturn(userEntity);

        UserDTO createdUser = userService.createUser(userDTO);

        assertEquals("John Doe", createdUser.getName());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void testGetAllUsers() {
        var userEntity = new User();
        userEntity.setName("John Doe");

        when(userRepository.findAll()).thenReturn(List.of(userEntity));

        List<UserDTO> users = userService.getAllUsers();

        assertEquals(1, users.size());
        assertEquals("John Doe", users.get(0).getName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById_UserExists() {
        Long userId = 1L;
        var userEntity = new User();
        userEntity.setId(userId);
        userEntity.setName("John Doe");

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        UserDTO user = userService.getUserById(userId);

        assertNotNull(user);
        assertEquals("John Doe", user.getName());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetUserById_UserDoesNotExist() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        UserDTO user = userService.getUserById(userId);

        assertNull(user);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testUpdateUser_UserExists() {
        Long userId = 1L;
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Jane Doe");

        var existingUser = new User();
        existingUser.setId(userId);
        existingUser.setName("John Doe");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any())).thenReturn(existingUser);

        UserDTO updatedUser = userService.updateUser(userId, userDTO);

        assertEquals("Jane Doe", updatedUser.getName());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void testUpdateUser_UserDoesNotExist() {
        Long userId = 1L;
        UserDTO userDTO = new UserDTO();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(userId, userDTO));
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testDeleteUser_UserExists() {
        Long userId = 1L;

        when(userRepository.existsById(userId)).thenReturn(true);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void testDeleteUser_UserDoesNotExist() {
        Long userId = 1L;

        when(userRepository.existsById(userId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(userId));
        verify(userRepository, times(1)).existsById(userId);
    }

}
