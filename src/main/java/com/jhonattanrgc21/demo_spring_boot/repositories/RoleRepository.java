package com.jhonattanrgc21.demo_spring_boot.repositories;

import com.jhonattanrgc21.demo_spring_boot.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
