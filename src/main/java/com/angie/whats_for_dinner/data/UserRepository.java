package com.angie.whats_for_dinner.data;

import com.angie.whats_for_dinner.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

