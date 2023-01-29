package com.example.bikeservice.backend.repository;

import com.example.bikeservice.backend.entity.Role;
import com.example.bikeservice.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);
}
