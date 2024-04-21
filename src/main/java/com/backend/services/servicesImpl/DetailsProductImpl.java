package com.backend.services.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dto.DetailProDTO; 
import com.backend.model.DetailsProduct;
import com.backend.repository.DetailsProductRepository;
import com.backend.services.DetailsProductService;

@Service
public class DetailsProductImpl implements DetailsProductService {
	
	@Autowired
	private DetailsProductRepository detailsProductRepository;
	
	@Override
	public List<DetailsProduct> getAllDetailsProduct() {
		return detailsProductRepository.findAll();
	}

	
	private List<DetailProDTO> convertToObjectDTO(List<Object[]> results) {
		List<DetailProDTO> DetailsProDTOList = new ArrayList<>();

		for (Object[] result : results) {
			DetailProDTO productDetailsDTO = new DetailProDTO();
			productDetailsDTO.setId((String) result[0]);
			productDetailsDTO.setName((String) result[1]);
			productDetailsDTO.setColor((String) result[2]);
			productDetailsDTO.setSize((String) result[3]);
			productDetailsDTO.setPurchases((Integer) result[4]);
			productDetailsDTO.setImg_url((String) result[5]);

			DetailsProDTOList.add(productDetailsDTO);
		}

		return DetailsProDTOList;
	}


	public List<DetailProDTO> getProductDetails() {
		List<Object[]> results = detailsProductRepository.getProductDetails();
		return convertToObjectDTO(results);
	}

	

}
