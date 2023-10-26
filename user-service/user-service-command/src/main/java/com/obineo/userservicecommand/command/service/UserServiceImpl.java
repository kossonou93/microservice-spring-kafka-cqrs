package com.obineo.userservicecommand.command.service;

import com.obineo.userservicecommand.command.dto.UserDTO;
import com.obineo.userservicecommand.command.dto.event.UserEvent;
import com.obineo.userservicecommand.command.model.User;
import com.obineo.userservicecommand.command.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public UserDTO createUser(UserEvent userEvent) {
        UserDTO userDTO = convertirEntityToDTO(userRepository.save(convertDTOToEntity(userEvent.getUserDTO())));
        UserEvent event = new UserEvent("CreateUser", userDTO);
        kafkaTemplate.send("user-event-topic", event);
        return userDTO;
    }

    @Override
    public UserDTO updateUser(UserEvent userEvent, Integer id) {
        User user = userRepository.findById(id).get();
        user.setNom(userEvent.getUserDTO().getNom());
        user.setUsername(userEvent.getUserDTO().getUsername());
        user.setEmail(userEvent.getUserDTO().getEmail());
        user.setPassword(userEvent.getUserDTO().getPassword());
        return convertirEntityToDTO(userRepository.save(user));
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
