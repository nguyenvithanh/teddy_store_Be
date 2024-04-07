package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.DetailProDTO;
import com.backend.model.DetailsProduct;
import com.backend.services.DetailsProductService;

@RestController
@RequestMapping("/teddy-store")
@CrossOrigin(value="http://localhost:3000/")
public class DetailsProductController {

	@Autowired
	private DetailsProductService detailsProductService;
	
	@GetMapping("/getAllDetailsProduct")
	public List<DetailsProduct> getAllDetailsProducts(){
		return detailsProductService.getAllDetailsProduct();
	}
  
    @GetMapping("/product-details")
    public List<DetailProDTO> getProductDetails() {
        return detailsProductService.getProductDetails();
    }
}
