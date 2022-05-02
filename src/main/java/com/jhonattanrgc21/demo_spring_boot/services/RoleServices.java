package com.jhonattanrgc21.demo_spring_boot.services;


import com.jhonattanrgc21.demo_spring_boot.entities.Role;
import com.jhonattanrgc21.demo_spring_boot.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoleServices  {
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Role save(Role entity) {
        return this.roleRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Iterable<Role> findAll(){
        return this.roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Role> findAll(Pageable pageable) {
        return this.roleRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Role> findById(Long id) {
        return this.roleRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        this.roleRepository.deleteById(id);
    }

}
