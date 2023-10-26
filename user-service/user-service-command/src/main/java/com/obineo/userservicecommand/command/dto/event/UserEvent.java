package com.obineo.userservicecommand.command.dto.event;

import com.obineo.userservicecommand.command.dto.UserDTO;
import com.obineo.userservicecommand.command.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEvent {

    private String eventType;
    private UserDTO userDTO;
}
