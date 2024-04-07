package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.RateProDTO;
import com.backend.model.Rate;
import com.backend.services.RateService;

@RestController
@RequestMapping("/teddy-store")
@CrossOrigin(value="http://localhost:3000/")
public class RateController {

	@Autowired
	private RateService rateService;
	
	@GetMapping("/getAllRate")
	public List<Rate> getAllRate(){
		return rateService.getAllRate();
	}
	@GetMapping("/getRatePro/{id}")
	public List<RateProDTO> getAllRate(@PathVariable String id){
		return rateService.getRateProduct(id);
	}

}
