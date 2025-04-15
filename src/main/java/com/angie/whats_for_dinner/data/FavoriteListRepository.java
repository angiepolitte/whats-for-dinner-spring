package com.angie.whats_for_dinner.data;

import com.angie.whats_for_dinner.models.FavoriteList;
import com.angie.whats_for_dinner.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavoriteListRepository extends JpaRepository<FavoriteList, Long> {
    List<FavoriteList> findByUser(User user);
}

//public interface FavoriteListRepository extends CrudRepository<FavoriteList, Long> {
//    List<FavoriteList> findByUser(User user);// To get favorite lists by user
//}
