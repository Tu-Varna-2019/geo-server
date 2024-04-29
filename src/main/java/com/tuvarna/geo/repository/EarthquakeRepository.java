package com.tuvarna.geo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.tuvarna.geo.entity.Earthquake;

@Repository
public interface EarthquakeRepository extends JpaRepository<Earthquake, Integer> {

    @Query(value = "SELECT * FROM gdeqk s WHERE ST_Contains(s.geom, ST_GeomFromText('POINT(' || :longitude || ' ' || :latitude || ')')) = true", nativeQuery = true)
    List<Earthquake> findByLongLatitude(double longitude, double latitude);
}
