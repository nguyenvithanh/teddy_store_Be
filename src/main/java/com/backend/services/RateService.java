package com.backend.services;

import java.util.List;

import com.backend.dto.PaginateDTO;
import com.backend.dto.RateDTO;
import com.backend.dto.RateProDTO;
import com.backend.model.Rate;
import org.springframework.data.domain.Pageable;

public interface RateService {

	public List<Rate> getAllRate();
	public List<RateProDTO> getRateProduct(String id);

	public PaginateDTO<List<RateDTO>> getRatePagination(Integer pageNumber, Integer pageSize);

	public boolean deleteRate(String id);
}
