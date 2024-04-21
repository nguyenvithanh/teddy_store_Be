package com.backend.services;

import java.util.List; 

import com.backend.dto.DetailProDTO; 
import com.backend.model.DetailsProduct;

public interface DetailsProductService {
	
	public List<DetailsProduct> getAllDetailsProduct();

    public List<DetailProDTO> getProductDetails();
   
}
