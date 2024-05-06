package com.tuvarna.geo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tuvarna.geo.entity.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    List<User> findByUsertypeType(String userType);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.isblocked = :isblocked WHERE u.email = :email")
    void updateIsBlockedByEmail(@Param("email") String email, @Param("isblocked") Boolean isblocked);

}
