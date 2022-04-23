package com.jhonattanrgc21.demo_spring_boot.interfaces;

import com.jhonattanrgc21.demo_spring_boot.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public interface UserInterface  {
    public User create(User entity);

    public Iterable<User> findAll();

    public Page<User> findAll(Pageable pageable);

    public Optional<User> findById(Long id);

    public void deleteById(Long id);
}
