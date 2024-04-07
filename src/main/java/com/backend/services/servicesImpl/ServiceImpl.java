package com.backend.services.servicesImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.backend.dto.ServiceProDTO;
import com.backend.repository.ServiceRepository;
import com.backend.services.ServiceService;

@Service
public class ServiceImpl implements ServiceService{

	@Autowired
	private ServiceRepository serviceRepository;

	@Override
	public List<com.backend.model.Service> getAllService() {
		return serviceRepository.findAll();
	}
	private List<ServiceProDTO> convertToObjectDTO(List<Object[]> results) {
		List<ServiceProDTO> ServiceProDTOList = new ArrayList<>();

		for (Object[] result : results) {
			ServiceProDTO ServiceProDTO = new ServiceProDTO();
			ServiceProDTO.setName((String) result[0]);
			ServiceProDTO.setPriceSv((BigDecimal) result[1]);
			ServiceProDTO.setProduct_count((Integer) result[2]);
			ServiceProDTO.setId((String) result[3]);

			ServiceProDTOList.add(ServiceProDTO);
		}

		return ServiceProDTOList;
	}

	@Override
	public List<ServiceProDTO> getProService(String id) {
		List<Object[]> results = serviceRepository.getProService(id);
		return convertToObjectDTO(results);
	}
}
