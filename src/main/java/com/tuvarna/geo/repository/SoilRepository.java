package com.tuvarna.geo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tuvarna.geo.entity.Soil;

@Repository
public interface SoilRepository extends JpaRepository<Soil, Integer> {

    @Query(value = "SELECT * FROM dsmw s WHERE ST_Contains(s.geom, ST_MakePoint(:latitude,:longitude)) = true", nativeQuery = true)
    List<Soil> findByLongitudeAndLatitude(@Param("longitude") double longitude, @Param("latitude") double latitude);

}
