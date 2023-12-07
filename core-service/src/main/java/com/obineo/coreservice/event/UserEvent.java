package com.obineo.coreservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEvent {

    private String eventType;
    private SecurityProperties.User user;
}
