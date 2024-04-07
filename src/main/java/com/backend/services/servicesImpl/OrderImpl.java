package com.backend.services.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.Order;
import com.backend.repository.OrderRepository;
import com.backend.services.OrderService;

@Service
public class OrderImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Order> getAllOder() {
		return orderRepository.findAll();
	}

	@Override
	public Order addNewOrder(Order order) {
		return orderRepository.save(order);
	}
}
