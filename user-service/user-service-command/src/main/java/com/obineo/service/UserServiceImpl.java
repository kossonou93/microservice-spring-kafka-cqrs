package com.obineo.service;

import com.obineo.coreservice.dto.UserDTO;
import com.obineo.event.UserEvent;
import com.obineo.model.User;
import com.obineo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private StreamBridge streamBridge;

    @Override
    public User createUser(UserEvent userEvent) {
        User userDTO = userRepository.save(userEvent.getUser());
        UserEvent event = new UserEvent("CreateUser", userDTO);
        kafkaTemplate.send("user-event-topic-2", event);
        //topics = "user-event-topic",groupId = "user-event-group"
        return userDTO;
    }

    @Override
    public User updateUser(UserEvent userEvent, Integer id) {
        User existingUser = userRepository.findById(id).get();
        User newUser = userEvent.getUser();
        existingUser.setNom(newUser.getNom());
        existingUser.setUsername(newUser.getUsername());
        existingUser.setEmail(newUser.getEmail());
        existingUser.setPassword(newUser.getPassword());
        User userDTO = userRepository.save(existingUser);
        UserEvent event = new UserEvent("UpdateEvent", userDTO);
        kafkaTemplate.send("user-event-topic", event);
        return userDTO;
    }

    @Override
    public User convertDTOToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    @Override
    public UserDTO convertirEntityToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
