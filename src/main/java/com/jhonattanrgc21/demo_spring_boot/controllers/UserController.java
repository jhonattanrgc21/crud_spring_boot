package com.jhonattanrgc21.demo_spring_boot.controllers;

import com.jhonattanrgc21.demo_spring_boot.entities.User;
import com.jhonattanrgc21.demo_spring_boot.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;


    @RequestMapping(method = RequestMethod.POST,value = "/create",produces = "application/json;charset=UTF-8", consumes = "application/json")
    public ResponseEntity<User> createUser(@RequestBody User json){
        return ResponseEntity.status(HttpStatus.CREATED).body(userServices.create(json));
    }
}
