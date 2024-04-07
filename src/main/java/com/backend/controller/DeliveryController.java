package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.Delivery;
import com.backend.services.DeliveryService;

@RestController
@RequestMapping("/teddy-store")
@CrossOrigin(value="http://localhost:3000/")
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;
	
	@GetMapping("/getAllDelivery")
	public List<Delivery> getAllDelivery(){
		return deliveryService.getAllDelivery();
	}
}
