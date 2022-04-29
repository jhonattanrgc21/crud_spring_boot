package com.jhonattanrgc21.demo_spring_boot.entities;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "roles")
@ApiObject
public class Role implements Serializable {
    private static final long serialVersionUID = 4259121614081485244L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiObjectField
    private Long id;

    @ApiObjectField(description = "Representa el nombre del rol")
    @Column(name = "name", nullable = false, length = 25, unique = true)
    private String name;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @Column(name = "update_at")
    @Temporal(TemporalType.DATE)
    private Date updateAt;

    public Role() {

    }

    public Role(String name) {
        this.name = name;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equals(getId(), role.getId()) &&
                Objects.equals(getName(), role.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
