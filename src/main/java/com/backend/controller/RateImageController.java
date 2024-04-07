package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.RateImage;
import com.backend.services.RateImageService;

@RestController
@RequestMapping("/teddy-store")
@CrossOrigin(value="http://localhost:3000/")
public class RateImageController {

	@Autowired
	private RateImageService rateImageService;
	
	@GetMapping("/getAllRateImage")
	public List<RateImage> getAllImageRate(){
		return rateImageService.getAllImageRate();
	}
}
