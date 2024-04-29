package com.backend.controller;

import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.AccountInfoDTO;
import com.backend.model.AccountInfo;
import com.backend.repository.AccountInfoRepository;
import com.backend.services.AccountInfoService;

@RestController
@RequestMapping("/teddy-store")
@CrossOrigin(value="http://localhost:3000/")
public class AccountInfoController {

	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private AccountInfoRepository accountInfoRepository;
	

	@GetMapping("/getAllAccountInfo")
	public List<AccountInfo> getAllAccountInfo() {
		return accountInfoService.getAllAccountInfo();
	}

	@GetMapping("/getDataAccWithId/{id}")
	public List<AccountInfoDTO> getDataAccWithId(@PathVariable String id){
		return accountInfoService.getDataWithId(id);
	}
	@PutMapping("/updateInfor/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable String id, @RequestBody AccountInfo updatedInfo) {
		AccountInfo accountInfo = accountInfoRepository.findById(id).orElse(null);
        if (accountInfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Update user info with new data
        accountInfo.setAvatar(updatedInfo.getAvatar());
        accountInfo.setName(updatedInfo.getName()); 
        accountInfo.setGender(updatedInfo.getGender());
        accountInfo.setBirthday(updatedInfo.getBirthday());
        accountInfo.setEmail(updatedInfo.getEmail());
        accountInfo.setPhone(updatedInfo.getPhone());
        // Update other fields as needed
        System.out.println(accountInfo.toString());
        accountInfoRepository.save(accountInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
