package com.backend.services;

import java.util.List;

import com.backend.dto.ImgProDTO;
import com.backend.model.ProductImage;

public interface ProductImageService {

	public List<ProductImage> getAllImageProduct();
	
	public List<ImgProDTO>getProductImages(String id);
}
