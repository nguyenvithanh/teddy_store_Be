package com.backend.services.servicesImpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dto.DetailOrd;
import com.backend.dto.DetailOrdDTO;
import com.backend.dto.ProductDTO;
import com.backend.model.DetailsOrder;
import com.backend.repository.DetailsOrderRepository;
import com.backend.services.DetailsOrderService;

@Service
public class DetailsOrderImpl implements DetailsOrderService {

    @Autowired
    private DetailsOrderRepository detailsOrderRepository;

    @Override
    public List<DetailsOrder> getAllDetailsOrder() {
        return detailsOrderRepository.findAll();
    }

    @Override
    public DetailsOrder addNewDetailOrder(DetailsOrder detailsOrder) {
        return detailsOrderRepository.save(detailsOrder);
    }

    private List<DetailOrdDTO> convertToProductsDTO(List<Object[]> results) {
		List<DetailOrdDTO> DetailOrdDTOList = new ArrayList<>();

		for (Object[] result : results) {
			DetailOrdDTO DetailOrdDTO = new DetailOrdDTO();
			DetailOrdDTO.setDate((Date) result[0]);
			DetailOrdDTO.setId((String) result[1]);
			DetailOrdDTO.setStatus((String) result[2]);
			DetailOrdDTO.setPrice_unit((BigDecimal) result[3]);

			DetailOrdDTOList.add(DetailOrdDTO);
		}

		return DetailOrdDTOList;
	}
	
	@Override
	public List<DetailOrdDTO> getAllDetailsOrders() {
		List<Object[]> results = detailsOrderRepository.getLatestOrderss();
		return convertToProductsDTO(results);
	}
    

}
