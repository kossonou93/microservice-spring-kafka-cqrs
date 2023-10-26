package com.obineo.userservicequery.query.dto.event;

import com.obineo.userservicequery.query.dto.UserDTO;
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
