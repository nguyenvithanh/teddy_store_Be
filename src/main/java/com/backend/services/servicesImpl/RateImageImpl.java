package com.backend.services.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.RateImage;
import com.backend.repository.RateImageRepository;
import com.backend.services.RateImageService;

@Service
public class RateImageImpl implements RateImageService{

	@Autowired
	private RateImageRepository rateImageRepository;

	@Override
	public List<RateImage> getAllImageRate() {
		return rateImageRepository.findAll();
	}
	

}
