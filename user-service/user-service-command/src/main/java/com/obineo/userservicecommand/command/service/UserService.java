package com.obineo.userservicecommand.command.service;

import com.obineo.userservicecommand.command.dto.UserDTO;
import com.obineo.userservicecommand.command.dto.event.UserEvent;
import com.obineo.userservicecommand.command.model.User;

public interface UserService {

    UserDTO createUser(UserEvent userEvent);

    UserDTO updateUser(UserEvent userEvent, Integer id);

    User convertDTOToEntity(UserDTO userDTO);

    UserDTO convertirEntityToDTO(User user);
}
