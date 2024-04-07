package com.backend.services.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dto.RateProDTO;
import com.backend.model.Rate;
import com.backend.repository.RateRepository;
import com.backend.services.RateService;

@Service
public class RateImpl implements RateService{

	@Autowired
	private RateRepository rateRepository;

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
}
