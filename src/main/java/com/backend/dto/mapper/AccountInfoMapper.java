package com.backend.dto.mapper;

import com.backend.dto.AccountInfoDTO;
import com.backend.model.Account;
import com.backend.model.AccountInfo;

public class AccountInfoMapper {
    public static AccountInfoDTO modelToDto(AccountInfo accountInfo, String address) {
        AccountInfoDTO accountInfoDTO = new AccountInfoDTO();
//        accountInfoDTO.setId(accountInfo.getId());
//        accountInfoDTO.setGender(accountInfo.getGender());
//        accountInfoDTO.setEmail(accountInfo.getEmail());
//        accountInfoDTO.setAvatar(accountInfo.getAvatar());
//        accountInfoDTO.setBirthday(accountInfo.getBirthday());
//        accountInfoDTO.setPhone(accountInfo.getPhone());
//        accountInfoDTO.setAddress(address);
//        accountInfoDTO.setName(accountInfo.getName());

        return accountInfoDTO;
    }
}
