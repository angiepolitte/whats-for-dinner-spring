package com.angie.whats_for_dinner.data;

import com.angie.whats_for_dinner.models.FavoriteList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteListRepository extends JpaRepository<FavoriteList, Long> {
    List<FavoriteList> findByUserId(Long userId);
}
