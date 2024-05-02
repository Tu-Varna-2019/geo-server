package com.tuvarna.geo.service;

import com.tuvarna.geo.entity.Earthquake;
import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.danger.DangerDTO;

public interface EarthquakeService {

    RestApiResponse<Earthquake> getEarthquake(DangerDTO user);

}
