package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.ImgProDTO; 
import com.backend.model.ProductImage;
import com.backend.services.ProductImageService;

@RestController
@RequestMapping("/teddy-store")
@CrossOrigin(value="http://localhost:3000/")
public class ProductImageController {

	@Autowired
	private ProductImageService productImageService;

	@GetMapping("/getAllImageProduct")
	public List<ProductImage> getAllImagePro() {
		return productImageService.getAllImageProduct();
	}
	@GetMapping("/getImgPro/{id}")
	public List<ImgProDTO> getProductImg(@PathVariable String id){
		return productImageService.getProductImages(id);
	}
	
}
