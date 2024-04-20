package com.backend.controller;

import java.util.List;

import com.backend.dto.PaginateDTO;
import com.backend.dto.RateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/rates")
	public PaginateDTO<List<RateDTO>> getAllRatePagination(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize ) {
		return rateService.getRatePagination(pageNumber,pageSize);
	}

	@DeleteMapping("/rates/{id}")
	public boolean deleteRates(@PathVariable("id") String id) {
		return rateService.deleteRate(id);
	}

	@GetMapping("/getRatePro/{id}")
	public List<RateProDTO> getAllRate(@PathVariable String id){
		return rateService.getRateProduct(id);
	}

}
