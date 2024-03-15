package com.tuvarna.geo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.tuvarna.geo.entity.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

    Optional<UserType> findByType(String type);
}