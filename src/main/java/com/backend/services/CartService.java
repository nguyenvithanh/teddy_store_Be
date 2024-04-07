package com.backend.services;

import java.util.List;

import com.backend.dto.CartDTO;
import com.backend.model.Cart; 

public interface CartService {

	public List<CartDTO> getAllCart(String id);

    public void deleteCart(String id);
    public void addToCart(Cart cart);
    
}
