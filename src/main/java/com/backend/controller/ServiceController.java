package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.ServiceProDTO;
import com.backend.model.Service;
import com.backend.services.ServiceService;

@RestController
@RequestMapping("/teddy-store")
@CrossOrigin(value="http://localhost:3000/")
public class ServiceController {

	@Autowired
	private ServiceService service;
	
	@GetMapping("/getAllService")
	public List<Service> getAllService(){
		return service.getAllService();
	}
	@GetMapping("/getProService/{id}")
	public List<ServiceProDTO> getProService(@PathVariable String id){
		return service.getProService(id);
	}
}
