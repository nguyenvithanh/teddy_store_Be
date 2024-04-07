package com.backend.services;

import java.util.List;

import com.backend.dto.ServiceProDTO;
import com.backend.model.Service;

public interface ServiceService {

	public List<Service> getAllService();
	public List<ServiceProDTO>getProService(String id);
}
