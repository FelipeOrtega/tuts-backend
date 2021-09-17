package com.anchietastudent.tuts.user.model;

import com.anchietastudent.tuts.user.model.enumeration.RoleName;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name="name", length = 20)
    private RoleName name;

    public Role() { }

    public Role(RoleName name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}

