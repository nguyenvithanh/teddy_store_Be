package com.backend.services;

import java.util.List;

import com.backend.model.Account;

public interface LoginAthenticationService {

	public List<Account> getAllAccount();

	boolean authenticateAcc(String username, String password);

	Account getInforByUsername(String username);

    public Account loginWithFacebook(Account acc);
}
