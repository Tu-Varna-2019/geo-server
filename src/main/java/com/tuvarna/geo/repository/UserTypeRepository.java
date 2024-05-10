package com.tuvarna.geo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tuvarna.geo.entity.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

    UserType findByType(String type);
}