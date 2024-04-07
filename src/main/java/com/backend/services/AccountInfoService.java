package com.backend.services;

import java.util.List;
import java.util.Optional;

import com.backend.dto.AccountInfoDTO;
import com.backend.model.AccountInfo;

public interface AccountInfoService {

	public List<AccountInfo> getAllAccountInfo();

	public List<AccountInfoDTO> getDataWithId(String id);
	
	boolean isExistEmail(String email);

	void saveAccountInfor(AccountInfo accountInfor);
}
