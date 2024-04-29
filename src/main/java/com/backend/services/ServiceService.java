package com.backend.services;

import java.util.List;

import com.backend.dto.ServiceProDTO;
import com.backend.model.Service;

public interface ServiceService {

	public List<Service> getAllService();
	public List<ServiceProDTO>getProService(String id);
	
	Object findAllService(int page, int limit);
	Object updateService(String id, String description, String idCategory, String name, double price, String image);

	Object deleteService(String id);

	Object searchService(String sv);
}
