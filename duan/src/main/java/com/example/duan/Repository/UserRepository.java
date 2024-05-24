package com.example.duan.Repository;

import com.example.duan.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUserName(String username);
    Optional<User> findByUserName(String username);
}
