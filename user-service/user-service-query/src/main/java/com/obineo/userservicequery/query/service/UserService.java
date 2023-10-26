package com.obineo.userservicequery.query.service;

import com.obineo.userservicequery.query.dto.UserDTO;
import com.obineo.userservicequery.query.model.User;
import java.util.List;

public interface UserService {


    User convertDTOToEntity(UserDTO userDTO);

    UserDTO convertirEntityToDTO(User user);

    List<UserDTO> listUser();

    UserDTO userById(Integer id);
}
