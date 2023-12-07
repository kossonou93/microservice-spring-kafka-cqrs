package com.obineo.service;

import com.obineo.coreservice.dto.UserDTO;
import com.obineo.event.UserEvent;
import com.obineo.model.User;

public interface UserService {

    User createUser(UserEvent userEvent);

    User updateUser(UserEvent userEvent, Integer id);

    User convertDTOToEntity(UserDTO userDTO);

    UserDTO convertirEntityToDTO(User user);
}
