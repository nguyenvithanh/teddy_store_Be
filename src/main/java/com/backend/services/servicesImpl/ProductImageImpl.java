package com.backend.services.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dto.ImgProDTO; 
import com.backend.model.ProductImage;
import com.backend.repository.ProductImageRepository;
import com.backend.services.ProductImageService;

@Service
public class ProductImageImpl implements ProductImageService {

	@Autowired
	private ProductImageRepository productImageRepository;

	@Override
	public List<ProductImage> getAllImageProduct() {
		return productImageRepository.findAll();
	}
	private List<ImgProDTO> convertToObjectDTO(List<Object[]> results) {
		List<ImgProDTO> ImgProDTOList = new ArrayList<>();

		for (Object[] result : results) {
			ImgProDTO ImgProDTO = new ImgProDTO();
			ImgProDTO.setImg_url((String) result[0]); 

			ImgProDTOList.add(ImgProDTO);
		}

		return ImgProDTOList;
	}

	@Override
	public List<ImgProDTO> getProductImages(String id) {
		List<Object[]> results = productImageRepository.getProductImages(id);
		return convertToObjectDTO(results);
	}

}
