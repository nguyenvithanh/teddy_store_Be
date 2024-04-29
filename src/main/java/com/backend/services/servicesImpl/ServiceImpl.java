package com.backend.services.servicesImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
 
import com.backend.dto.ServiceProDTO;
import com.backend.model.Category;
import com.backend.repository.ServiceRepository;
import com.backend.services.ServiceService;
import com.backend.util.RandomUtil;

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
	
	@Override
    public Object findAllService(int page, int limit) {
        return serviceRepository.findAll(PageRequest.of(page, limit));
    }

    @Override
    public Object updateService(
            String id, String description, String idCategory, String name, double price, String image
    ) {
        if (serviceRepository.existsByNameAndIdIsNot(name, id)) {
            return "SERVICE_EXISTED";
        }
        var service = serviceRepository.findById(id);
        var lastService = serviceRepository.findLastService();
        var idService = RandomUtil.getNextId(null, "SV");
        if (lastService.isPresent()) {
            idService = RandomUtil.getNextId(lastService
                                                     .get()
                                                     .getId(), "SV");
        }
        var newCategory = new Category();
        newCategory.setId(idCategory);
        if (service.isPresent()) {
            service
                    .get()
                    .setName(name);
            service
                    .get()
                    .setDescription(description);
            service
                    .get()
                    .setPrice(price);
            service
                    .get()
                    .setCategory(newCategory);
            if (image != null && !image.isEmpty()) {
                service
                        .get()
                        .setImage(image);
            }
            serviceRepository.save(service.get());
        } else {
            var newService = new com.backend.model.Service();
            newService.setId(idService);
            newService.setName(name);
            newService.setDescription(description);
            newService.setPrice(price);
            newService.setCategory(newCategory);
            if (image != null && !image.isEmpty()) {
                newService.setImage(image);
            }
            serviceRepository.save(newService);
        }
        return "OK";
    }

    @Override
    public Object deleteService(String id) {
        serviceRepository.deleteById(id);
        return "OK";
    }

    @Override
    public Object searchService(String sv) {
        return serviceRepository.searchByName(sv.trim().toLowerCase(), PageRequest.of(0, 10));
    } 

}
