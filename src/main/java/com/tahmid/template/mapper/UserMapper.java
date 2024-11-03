package com.tahmid.template.mapper;

import com.tahmid.template.dto.UserDTO;
import com.tahmid.template.model.User;
import org.springframework.beans.BeanUtils;

public class UserMapper {

    public static User toEntity(UserDTO userDTO) {

        var user = new User();
        BeanUtils.copyProperties(userDTO,user);
        return user;

    }

    public static UserDTO toDTO(User user) {

        var userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;

    }






}
