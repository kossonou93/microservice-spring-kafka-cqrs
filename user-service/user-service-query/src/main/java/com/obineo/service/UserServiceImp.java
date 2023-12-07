package com.obineo.service;

import com.obineo.coreservice.dto.UserDTO;
import com.obineo.event.UserEvent;
import com.obineo.model.User;
import com.obineo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User convertDTOToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    @Override
    public UserDTO convertirEntityToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }

    @Override
    public User userById(Integer id) {
        return userRepository.findById(id).get();
    }

    @KafkaListener(topics = "user-event-topic-2",groupId = "user-event-group")
    public void processUserEvent(UserEvent userEvent){
        User user = userEvent.getUser();
        if (userEvent.getEventType().equals("CreateUser")) {
            userRepository.save(user);
        }
        if (userEvent.getEventType().equals("UpdateUser")) {
            User userExisting = userRepository.findById(user.getId()).get();
            userExisting.setNom(user.getNom());
            userExisting.setEmail(user.getEmail());
            userExisting.setUsername(user.getUsername());
            userExisting.setPassword(user.getPassword());
            userRepository.save(userExisting);
        }
    }
}
