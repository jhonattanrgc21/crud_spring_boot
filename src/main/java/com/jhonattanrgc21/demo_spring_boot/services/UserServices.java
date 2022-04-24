package com.jhonattanrgc21.demo_spring_boot.services;

import com.jhonattanrgc21.demo_spring_boot.entities.User;
import com.jhonattanrgc21.demo_spring_boot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User save(User entity) {
        return this.userRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Iterable<User> findAll() {
        return this.userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Transactional
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }
}
