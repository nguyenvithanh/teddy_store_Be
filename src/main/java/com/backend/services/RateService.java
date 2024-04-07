package com.backend.services;

import java.util.List;

import com.backend.dto.RateProDTO;
import com.backend.model.Rate;

public interface RateService {

	public List<Rate> getAllRate();
	public List<RateProDTO> getRateProduct(String id);
}
