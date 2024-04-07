package com.backend.controller;

import java.util.List; 

import com.backend.dto.AccountInfoDTO;
import com.backend.services.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.backend.model.AccountInfo;

@RestController
@RequestMapping("/teddy-store")
@CrossOrigin(value="http://localhost:3000/")
public class AccountInfoController {

	@Autowired
	private AccountInfoService accountInfoService;

	@GetMapping("/getAllAccountInfo")
	public List<AccountInfo> getAllAccountInfo() {
		return accountInfoService.getAllAccountInfo();
	}

	@GetMapping("/getDataAccWithId/{id}")
	public List<AccountInfoDTO> getDataAccWithId(@PathVariable String id){
		return accountInfoService.getDataWithId(id);
	}
}
