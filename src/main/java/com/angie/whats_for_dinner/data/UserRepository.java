package com.angie.whats_for_dinner.data;

import com.angie.whats_for_dinner.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
//@Repository
//public interface UserRepository extends CrudRepository<User, Long> {
//    Optional<User> findByUsername(String username);
//}

