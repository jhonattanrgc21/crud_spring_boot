package com.jhonattanrgc21.demo_spring_boot.controllers;

import com.jhonattanrgc21.demo_spring_boot.entities.Role;
import com.jhonattanrgc21.demo_spring_boot.entities.User;
import com.jhonattanrgc21.demo_spring_boot.services.RoleServices;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Api(name = "Roles", description = "Operaciones asociadas al manejo de Roles de usuario", group = "Roles")
@RestController
@RequestMapping("/role")
@CrossOrigin("*")
public class RoleController {
    @Autowired
    private RoleServices roleServices;

    @PostMapping("/create")
    @ApiMethod(description = "Permite crear nuevos roles de usuario")
    @ApiResponseObject
    @ResponseBody
    public ResponseEntity<?> createUser(@ApiBodyObject(clazz = Role.class) @RequestBody Role json) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleServices.save(json));
    }

    @GetMapping("/{id}")
    @ApiMethod(description = "Retorna un rol mediante su ID")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<Role> role = this.roleServices.findById(id);
        if (!role.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(role);
    }


    @GetMapping("/all")
    @ApiMethod(description = "Retorna una lista con todos los roles de usuario registrados")
    public List<Role> findAll(){
        List<Role> roles = StreamSupport.stream(this.roleServices.findAll().spliterator(), false).collect(Collectors.toList());
        return roles;
    }

    @PutMapping("/{id}")
    @ApiMethod(description = "Permite actualizar datos de un rol de usuario mediante su ID")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Role json) {
        Optional<Role> optionalUser = this.roleServices.findById(id);
        if (optionalUser.isPresent()) {
            Role role = optionalUser.get();
            role.setName(!json.getName().isEmpty() && !json.getName().isBlank() ? json.getName() : role.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(this.roleServices.save(role));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ApiMethod(description = "Permite eliminar roles de usuario mediante su ID")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        Optional<Role> role = this.roleServices.findById(id);
        if (!role.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        this.roleServices.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
