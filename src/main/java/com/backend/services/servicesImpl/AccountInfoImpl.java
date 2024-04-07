package com.backend.services.servicesImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dto.AccountInfoDTO;
import com.backend.model.AccountInfo;
import com.backend.repository.AccountInfoRepository;
import com.backend.services.AccountInfoService;
import com.backend.util.RandomUtil;

import jakarta.transaction.Transactional;

@Service
public class AccountInfoImpl implements AccountInfoService {

	@Autowired
	private AccountInfoRepository accountInfoRepository;

	@Override
	public List<AccountInfo> getAllAccountInfo() {
		return accountInfoRepository.findAll();
	}

	@Override
	public List<AccountInfoDTO> getDataWithId(String id) {
		List<Object[]> results = accountInfoRepository.findDataAccWithId(id);
		return convertToObjectDTO(results);
	}

    private List<AccountInfoDTO> convertToObjectDTO(List<Object[]> results) {
        List<AccountInfoDTO> cartDetailsDTOList = new ArrayList<>();

        for (Object[] result : results) {
            AccountInfoDTO DetailsDTO = new AccountInfoDTO();

            DetailsDTO.setId((String) result[0]);
            DetailsDTO.setAvatar((String) result[1]);
            DetailsDTO.setName((String) result[2]);
            DetailsDTO.setGender((Boolean) result[3]);
            DetailsDTO.setEmail((String) result[4]);
            DetailsDTO.setPhone((Integer) result[5]);
            DetailsDTO.setAddress((String) result[6]);
            DetailsDTO.setBirthday((Date) result[7]);

            cartDetailsDTOList.add(DetailsDTO);
        }

        return cartDetailsDTOList;
    }
    @Override
	public boolean isExistEmail(String email) {
		return accountInfoRepository.findByEmail(email)
				.isPresent();
	}

	@Override
	@Transactional
	public void saveAccountInfor(AccountInfo accountInfor) {
		var lastAccInfo = accountInfoRepository.findLastAccountInfor();
		if(lastAccInfo.isPresent()){
			accountInfor.setId(RandomUtil.getNextId(lastAccInfo.get().getId(), "IA"));
		}else {
			accountInfor.setId(RandomUtil.getNextId(null, "IA"));
		}
		accountInfoRepository.save(accountInfor);
	}
}
