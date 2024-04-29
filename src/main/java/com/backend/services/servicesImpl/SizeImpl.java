package com.backend.services.servicesImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.backend.dto.SizeDTO;
import com.backend.model.Size;
import com.backend.repository.SizeRepository;
import com.backend.services.SizeService;
import com.backend.util.RandomUtil;

@Service
public class SizeImpl implements SizeService {

	@Autowired
	private SizeRepository sizeRepository;

	@Override
	public List<Size> getAllSize() {
		return sizeRepository.findAll(); 
	}

	private List<SizeDTO> convertToObjectDTO(List<Object[]> results) {
		List<SizeDTO> sizeDetailsDTOList = new ArrayList<>();

		for (Object[] result : results) {
			SizeDTO DetailsDTO = new SizeDTO();
			DetailsDTO.setId((String) result[0]);
			DetailsDTO.setSize_no((String) result[1]);
			DetailsDTO.setPrice(((BigDecimal) result[2]).intValue());
			DetailsDTO.setPrice_sale(((BigDecimal) result[3]).intValue());
			DetailsDTO.setQuantity((Integer) result[4]);
			DetailsDTO.setCount((Integer) result[5]);

			sizeDetailsDTOList.add(DetailsDTO);
		}

		return sizeDetailsDTOList;
	}

	public List<SizeDTO> getSizeWhereId(String id) {
		List<Object[]> results = sizeRepository.findByIdWhere(id);
		return convertToObjectDTO(results);
	}
	@Override
    public Object getAllSize(int page, int size) {
        var pageable = PageRequest.of(page, size);
        return sizeRepository.findAll(pageable);
    }


	   @Override
	    public Object updateSize(String id, String size_no) {
			
	        var size = sizeRepository.findById(id);
		
	        if (size.isPresent()) {
	            
	            if(sizeRepository.existsBySize_noAndIdIsNot(size_no, id) > 0) {
	                return "SIZE_EXISTED";
	            }
				
	            var size1 = size.get();
	            size1.setSize_no(size_no);
	            sizeRepository.save(size1);
	        } else {
	            
	            if(sizeRepository.existsBySize_no(size_no) > 0) {
	                return "SIZE_EXISTED";
	            }
				
	            var lastSize = sizeRepository.findLastSize();
	            var newSize = new Size();
				
	            if (lastSize.isPresent()) {
	                newSize.setId(RandomUtil.getNextId(lastSize.get().getId(), "SI"));
	            } else {
					
	                newSize.setId(RandomUtil.getNextId(null, "SI"));
	            }
	            newSize.setSize_no(size_no);
	            sizeRepository.save(newSize);
	        }
	        return "OK";
	    }

    @Override
    public Object searchSize(String sizeNo) {
		// tim kiem size theo size_no
        return sizeRepository.findAllByName(sizeNo, PageRequest.of(0, 10));
    }
    
}
