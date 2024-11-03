package com.tahmid.template.service;

import com.tahmid.template.dto.UserDTO;
import com.tahmid.template.model.User;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);


}
