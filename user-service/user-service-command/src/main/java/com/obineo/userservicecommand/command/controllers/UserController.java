package com.obineo.userservicecommand.command.controllers;

import com.obineo.userservicecommand.command.dto.UserDTO;
import com.obineo.userservicecommand.command.dto.event.UserEvent;
import com.obineo.userservicecommand.command.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/obineo/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserEvent request){
        System.out.println("request ==> " + request.getUserDTO());
        UserDTO userDTO = userService.createUser(request);
        System.out.println("userDTO ==> " + userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserEvent request, @PathVariable Integer id){
        UserDTO userDTO = userService.updateUser(request, id);
        return new ResponseEntity<>(userDTO, HttpStatus.UPGRADE_REQUIRED);
    }
}
