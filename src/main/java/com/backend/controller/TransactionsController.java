package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.Transactions;
import com.backend.services.TransactionsService;

@RestController
@RequestMapping("/teddy-store")
@CrossOrigin(value="http://localhost:3000/")
public class TransactionsController {

	@Autowired
	private TransactionsService transactionsService;
	
	@GetMapping("/getAllTransactions")
	public List<Transactions> getAllTransactions(){
		return transactionsService.getAllTransactions();
	}
}
