package com.backend.services;

import java.util.List;

import com.backend.model.Account;

public interface AccountService {

	public List<Account> getAllAccount();

	boolean authenticateAcc(String username, String password);

	Account getInfoByUsername(String username);

    public Account loginWithFacebook(Account acc);
    
    boolean isExistId(String id);
	boolean isExistUsername(String username);
	boolean registerAccount(Account acc);
	String resetPassword(String email); 
}
