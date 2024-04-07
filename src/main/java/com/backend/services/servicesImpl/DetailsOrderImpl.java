package com.backend.services.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dto.DetailOrd;
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

	@Override
	public List<DetailOrd> getAllDetailsOrders() {
		// TODO Auto-generated method stub
		return null;
	}
    

}
