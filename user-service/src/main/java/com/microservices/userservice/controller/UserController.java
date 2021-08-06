package com.microservices.userservice.controller;

import com.microservices.userservice.entity.UserEntity;
import com.microservices.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/",  consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserEntity saveUser(@RequestBody UserEntity userEntity){
        return userService.saveUser(userEntity);
    }
    @GetMapping("/")
    public List<UserEntity> getAllUser(){
        return userService.GetALlUsers();
    }

    @GetMapping("/{userId}")
    public UserEntity getUser(@PathVariable("userId") Long userId){
        return userService.findUserById(userId);
    }

}
