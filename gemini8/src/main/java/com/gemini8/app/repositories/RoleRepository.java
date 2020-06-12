package com.gemini8.app.repositories;

import com.gemini8.app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
    //@Query(value="SELECT ROLE FROM ROLES")
    List<Role> findAll();
}