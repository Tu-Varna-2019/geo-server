package com.tuvarna.geo.service;

import com.tuvarna.geo.entity.Earthquake;
import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.risk.RiskDTO;

public interface EarthquakeService {

    RestApiResponse<Earthquake> getEarthquake(RiskDTO riskDTO);

}
