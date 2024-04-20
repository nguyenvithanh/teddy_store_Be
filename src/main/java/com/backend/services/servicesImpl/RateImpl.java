package com.backend.services.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import com.backend.dto.CustomerDto;
import com.backend.dto.PaginateDTO;
import com.backend.dto.RateDTO;
import com.backend.dto.mapper.RateMapper;
import com.backend.repository.RateImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.dto.RateProDTO;
import com.backend.model.Rate;
import com.backend.repository.RateRepository;
import com.backend.services.RateService;

@Service
public class RateImpl implements RateService{

	@Autowired
	private RateRepository rateRepository;

	@Autowired
	private RateImageRepository rateImageRepository;

	@Override
	public List<Rate> getAllRate() {
		return rateRepository.findAll();
	}
	private List<RateProDTO> convertToObjectDTO(List<Object[]> results) {
		List<RateProDTO> RateProDTOList = new ArrayList<>();

		for (Object[] result : results) {
			RateProDTO RateProDTO = new RateProDTO();
			RateProDTO.setStar_no((Integer) result[0]);
			RateProDTO.setNumber_rate((Integer) result[1]);
			RateProDTO.setQuantity((Integer) result[2]); 

			RateProDTOList.add(RateProDTO);
		}

		return RateProDTOList;
	}

	@Override
	public List<RateProDTO> getRateProduct(String id) {
		List<Object[]> results = rateRepository.getRateProduct(id);
		return convertToObjectDTO(results);
	}

	@Override
	public PaginateDTO<List<RateDTO>> getRatePagination(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber,pageSize);
		Page<Rate> page = rateRepository.findAll(pageable);
		List<RateDTO> rates = new ArrayList<>();

		for(Rate rate: page.getContent()) {
			rates.add(RateMapper.toRateDto(rate));
		}

		PaginateDTO<List<RateDTO>> dataResponse = new PaginateDTO<>(
				page.getNumber(),
				page.getSize(),
				page.getTotalElements(),
				page.getTotalPages(),
				page.isLast(),
				rates
		);
		return dataResponse;
	}

	@Override
	public boolean deleteRate(String id) {
		Rate rate = rateRepository.findById(id).orElseGet(null);
		if (rate != null) {
			try {
				rateImageRepository.deleteByIdRate(rate.getId());
				rateRepository.delete(rate);
				return true;
			} catch (Exception ex) {
				ex.printStackTrace(); // In ra thông tin lỗi
			}
		}
		return false;
	}
}
