package com.tuvarna.geo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tuvarna.geo.entity.Soil;

@Repository
public interface SoilRepository extends JpaRepository<Soil, Integer> {

    @Query(value = "SELECT * FROM dsmw ds WHERE ST_Contains(ds.geom, ST_MakePoint(:latitude,:longitude)) = true", nativeQuery = true)
    Soil findByLongitudeAndLatitude(@Param("longitude") double longitude, @Param("latitude") double latitude);

}
