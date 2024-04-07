package com.backend.controller;
import java.util.List;

import com.backend.dto.CartDTO;
import com.backend.model.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.services.CartService;

@RestController
@RequestMapping("/teddy-store")
@CrossOrigin(value="http://localhost:3000/")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@GetMapping("/getAllCart/{id}")
	public List<CartDTO> getAllCart(@PathVariable String id){
		return cartService.getAllCart(id);
	}

	@DeleteMapping("/delete-cart/{id}")
	ResponseEntity<?> doDeleteCart(@PathVariable String id){
		cartService.deleteCart(id);
		return ResponseEntity.ok("Delete successfully!");
	}
	@PostMapping(value ="/add")
    public ResponseEntity<String> addToCart(@RequestBody Cart cart) {
		System.out.println(cart);
		cartService.addToCart(cart);
		return ResponseEntity.ok("Add vào giỏ hàng thành công");
        
    }
}
