package com.backend.services;

import java.util.List;

import com.backend.dto.DetailOrd;
import com.backend.dto.DetailOrdDTO;
import com.backend.model.DetailsOrder;

public interface DetailsOrderService {

	public List<DetailsOrder> getAllDetailsOrder();

	public DetailsOrder addNewDetailOrder(DetailsOrder detailsOrder);
	
	public List<DetailOrdDTO> getAllDetailsOrders(); 
}
