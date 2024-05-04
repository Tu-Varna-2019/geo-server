package com.tuvarna.geo.service;

import com.tuvarna.geo.entity.Soil;
import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.risk.RiskDTO;

public interface SoilService {

    RestApiResponse<Soil> getSoil(RiskDTO riskDTO);

}
