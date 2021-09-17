package com.anchietastudent.tuts.user.repository;

import com.anchietastudent.tuts.user.model.Role;
import com.anchietastudent.tuts.user.model.enumeration.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByName(RoleName name);
}
