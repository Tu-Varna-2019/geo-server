package com.tuvarna.geo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tuvarna.geo.entity.Earthquake;

@Repository
public interface EarthquakeRepository extends JpaRepository<Earthquake, Integer> {

    @Query(value = "SELECT * FROM  gdeqk gd WHERE ST_Contains(gd.geom, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)) = true", nativeQuery = true)
    Earthquake findByLongLatitude(double longitude, double latitude);
}
