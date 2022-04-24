package com.jhonattanrgc21.demo_spring_boot.controllers;

import com.jhonattanrgc21.demo_spring_boot.entities.User;
import com.jhonattanrgc21.demo_spring_boot.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User json) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userServices.save(json));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<User> user = this.userServices.findById(id);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/findByUsername")
    public ResponseEntity<?> findByUsername(@Param("username") String username) {
        Optional<User> user = this.userServices.findByUsername(username);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<?> findByEmail(@Param("email") String email) {
        Optional<User> user = this.userServices.findByEmail(email);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/all")
    public List<User> findAll(){
        List<User> users = StreamSupport.stream(this.userServices.findAll().spliterator(), false).collect(Collectors.toList());
        return users;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody User json) {
        Optional<User> optionalUser = this.userServices.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(!json.getFirstName().isEmpty() && !json.getFirstName().isBlank() ? json.getFirstName() : user.getFirstName());
            user.setLastName(!json.getLastName().isEmpty() && !json.getLastName().isBlank() ? json.getLastName() : user.getLastName());
            user.setUsername(!json.getUsername().isEmpty() && !json.getUsername().isBlank() ? json.getUsername() : user.getUsername());
            user.setEmail(!json.getEmail().isEmpty() && !json.getEmail().isBlank() ? json.getEmail() : user.getEmail());
            user.setPassword(!json.getPassword().isEmpty() && !json.getPassword().isBlank() ? json.getPassword() : user.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(this.userServices.save(user));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        Optional<User> user = this.userServices.findById(id);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        this.userServices.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
