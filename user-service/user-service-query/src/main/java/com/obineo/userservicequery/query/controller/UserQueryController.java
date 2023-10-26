package com.obineo.userservicequery.query.controller;

import com.obineo.userservicequery.query.dto.UserDTO;
import com.obineo.userservicequery.query.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/query/users")
public class UserQueryController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> listUser(){
        return new ResponseEntity<>(userService.listUser(), HttpStatus.FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> user(@PathVariable Integer id){
        return new ResponseEntity<>(userService.userById(id), HttpStatus.FOUND);
    }
}
