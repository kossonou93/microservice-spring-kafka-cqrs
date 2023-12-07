package com.obineo.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.obineo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserEvent {

    private String eventType;
    private User user;
}
