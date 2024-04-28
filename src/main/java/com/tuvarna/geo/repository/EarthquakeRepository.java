package com.tuvarna.geo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tuvarna.geo.entity.Earthquake;

@Repository
public interface EarthquakeRepository extends JpaRepository<Earthquake, Integer> {

    Earthquake findByLongLatitude(long longitude, long latitude);
}
