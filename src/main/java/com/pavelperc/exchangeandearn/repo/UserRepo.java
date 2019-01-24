package com.pavelperc.exchangeandearn.repo;

import com.pavelperc.exchangeandearn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
