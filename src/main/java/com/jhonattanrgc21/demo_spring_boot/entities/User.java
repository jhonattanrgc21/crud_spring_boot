package com.jhonattanrgc21.demo_spring_boot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "users")
@ApiObject
public class User implements Serializable{
    private static final long serialVersionUID = 3074616617828250578L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiObjectField
    private Long id;

    @ApiObjectField(description = "Representa el nombre del usuario")
    @Column(name = "first_name", nullable = false, length = 25, updatable = true)
    private String firstName;

    @ApiObjectField(description = "Representa el apellido del usuario")
    @Column(name = "last_name", nullable = false, length = 25, updatable = true)
    private String lastName;

    @ApiObjectField(description = "Representa el nick del usuario")
    @Column(name = "username", nullable = false, unique = true, length = 15)
    private String username;

    @ApiObjectField(description = "Representa el correo electronico del usuario")
    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

    @ApiObjectField(description = "Representa la contraseña del usuario")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @Column(name = "update_at")
    @Temporal(TemporalType.DATE)
    private Date updateAt;

    // Constructor
    public User() {
    }

    public User(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @PrePersist
    void prePersist() {
        this.createAt = new Date();
        this.updateAt = new Date();
    }

    @PreUpdate
    void preUpdate() {
        this.updateAt = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
