package com.backend.services.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.Transactions;
import com.backend.repository.TransactionsRepository;
import com.backend.services.TransactionsService;

@Service
public class TransactionsImpl implements TransactionsService{

	@Autowired
	private TransactionsRepository transactionsRepository;

	@Override
	public List<Transactions> getAllTransactions() {
		return transactionsRepository.findAll();
	}
}
