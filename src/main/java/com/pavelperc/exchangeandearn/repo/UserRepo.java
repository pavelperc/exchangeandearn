package com.pavelperc.exchangeandearn.repo;

import com.pavelperc.exchangeandearn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    
    Optional<User> findByLogin(String login);
}
