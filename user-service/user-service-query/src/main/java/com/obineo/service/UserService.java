package com.obineo.service;

import com.obineo.coreservice.dto.UserDTO;
import com.obineo.model.User;
import java.util.List;

public interface UserService {


    User convertDTOToEntity(UserDTO userDTO);

    UserDTO convertirEntityToDTO(User user);

    List<User> listUser();

    User userById(Integer id);
}
