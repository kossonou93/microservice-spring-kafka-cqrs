package com.obineo.controllers;

import com.obineo.event.UserEvent;
import com.obineo.model.User;
import com.obineo.service.UserServiceImpl;
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
    public ResponseEntity<User> createUser(@RequestBody UserEvent request){
        System.out.println("request ==> " + request.getUser());
        User user = userService.createUser(request);
        System.out.println("userDTO ==> " + user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserEvent request, @PathVariable Integer id){
        User user = userService.updateUser(request, id);
        return new ResponseEntity<>(user, HttpStatus.UPGRADE_REQUIRED);
    }
}
