package com.tahmid.template.service;

import com.tahmid.template.dto.UserDTO;
import com.tahmid.template.exception.ResourceNotFoundException;
import com.tahmid.template.mapper.UserMapper;
import com.tahmid.template.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;

    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        final var user = UserMapper.toEntity(userDTO);
        return UserMapper.toDTO(userRepository.save(user));

    }

    @Override
    public List<UserDTO> getAllUsers() {

        var users = userRepository.findAll();
        return users.stream().map(UserMapper::toDTO).toList();

    }

    @Override
    public UserDTO getUserById(Long id) {

        var user = userRepository.findById(id);
        return user.stream().map(UserMapper::toDTO).findFirst().orElse(null);

    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {

        var existingUser = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found"));
        BeanUtils.copyProperties(userDTO,existingUser);
        return UserMapper.toDTO(userRepository.save(existingUser));

    }

    @Override
    public void deleteUser(Long id) {

        if (!userRepository.existsById(id)) {

            throw new ResourceNotFoundException("User not found");

        }

        userRepository.deleteById(id);

    }

}
