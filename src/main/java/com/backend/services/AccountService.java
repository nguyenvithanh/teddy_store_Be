package com.backend.services;

import java.util.List;

import com.backend.dto.CustomerDto;
import com.backend.dto.PaginateDTO;
import com.backend.model.Account;

public interface AccountService {

	public List<Account> getAllAccount();

	boolean authenticateAcc(String username, String password);

	Account getInfoByUsername(String username);
	Object getInfoByUsernameV2(String username);

    public Account loginWithFacebook(Account acc);
    
    boolean isExistId(String id);
	boolean isExistUsername(String username);
	boolean registerAccount(Account acc);
	String resetPassword(String email);

	public PaginateDTO<List<CustomerDto>> getCustomerSearchAndPagination(String query, Boolean active, Integer pageNumber, Integer pageSize);

	public boolean updateActiveCustomer(String id,Boolean active);
}
