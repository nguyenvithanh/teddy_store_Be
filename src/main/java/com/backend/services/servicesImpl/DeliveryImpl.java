package com.backend.services.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.Delivery;
import com.backend.repository.DeliveryRepository;
import com.backend.services.DeliveryService;

@Service
public class DeliveryImpl implements DeliveryService{
	
	@Autowired
	private DeliveryRepository deliveryRepository;

	@Override
	public List<Delivery> getAllDelivery() {
		return deliveryRepository.findAll();
	}

}
