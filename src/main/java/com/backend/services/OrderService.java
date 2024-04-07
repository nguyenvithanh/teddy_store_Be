package com.backend.services;

import java.util.List;

import com.backend.model.Order;

public interface OrderService {

	public List<Order> getAllOder();

	public Order addNewOrder(Order order);
}
