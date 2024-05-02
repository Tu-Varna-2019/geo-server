package com.tuvarna.geo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuvarna.geo.entity.Earthquake;
import com.tuvarna.geo.exception.BadRequestError;
import com.tuvarna.geo.repository.EarthquakeRepository;
import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.danger.DangerDTO;

import jakarta.transaction.Transactional;

@Service
public class EarthquakeServiceImpl implements EarthquakeService {
    private static final Logger logger = LogManager.getLogger(EarthquakeServiceImpl.class.getName());

    private EarthquakeRepository earthquakeRepository;

    @Autowired
    public EarthquakeServiceImpl(EarthquakeRepository earthquakeRepository) {
        this.earthquakeRepository = earthquakeRepository;
    }

    @Override
    @Transactional
    @SuppressWarnings({ "squid:S3457", "squid:S2629" })
    public RestApiResponse<Earthquake> getEarthquake(DangerDTO dangerDTO) {

        Earthquake earthquakeDb = earthquakeRepository.findByLongLatitude(dangerDTO.getLongitude(),
                dangerDTO.getLatitude());
        if (earthquakeDb == null)
            throw new BadRequestError("Error, earthquake not found!");

        logger.info(
                "Earthquake found from given longitude/langiude {}, {}",
                dangerDTO.getLongitude(), dangerDTO.getLatitude());
        logger.info("Now sending earthquake data with id: {}", earthquakeDb.getId());

        return new RestApiResponse<>(earthquakeDb, "Earthquake found with id: " + earthquakeDb.getId(), 201);
    }
}
