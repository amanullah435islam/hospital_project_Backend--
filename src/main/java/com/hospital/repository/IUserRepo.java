package com.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hospital.model.User;

@Repository
public interface IUserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}