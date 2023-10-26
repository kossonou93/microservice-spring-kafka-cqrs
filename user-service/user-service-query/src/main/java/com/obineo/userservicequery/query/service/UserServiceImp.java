package com.obineo.userservicequery.query.service;

import com.obineo.userservicequery.query.dto.UserDTO;
import com.obineo.userservicequery.query.dto.event.UserEvent;
import com.obineo.userservicequery.query.model.User;
import com.obineo.userservicequery.query.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<UserDTO> listUser() {
        return userRepository.findAll()
                .stream()
                .map(this::convertirEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO userById(Integer id) {
        return convertirEntityToDTO(userRepository.findById(id).get());
    }

    @KafkaListener(topics = "user-event-topic",groupId = "user-event-group")
    public void processUserEvent(UserEvent userEvent){
        UserDTO user = userEvent.getUserDTO();
        if (userEvent.getEventType().equals("CreateUser")) {
            userRepository.save(convertDTOToEntity(user));
        }
        if (userEvent.getEventType().equals("UpdateUser")) {
            UserDTO userExist = convertirEntityToDTO(userRepository.findById(user.getId()).get());
            userExist.setNom(user.getNom());
            userExist.setEmail(user.getEmail());
            userExist.setUsername(user.getUsername());
            userExist.setPassword(user.getPassword());
            userRepository.save(convertDTOToEntity(userExist));
        }
    }
}
