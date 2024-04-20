package com.backend.dto.mapper;

import com.backend.dto.AccountInfoDTO;
import com.backend.dto.RateDTO;
import com.backend.model.AccountInfo;
import com.backend.model.Address;
import com.backend.model.Rate;

import java.util.Iterator;

public class RateMapper {
    public static RateDTO toRateDto(Rate rate) {
        RateDTO rateDto = new RateDTO();
        rateDto.setId(rate.getId());
        rateDto.setId_product(rate.getDetailsProduct().getProduct().getId());
        rateDto.setStar(rate.getStar_no());
        rateDto.setDescription(rate.getDescription());
        Iterator iterator1 = rate.getAccount().getAccountInfo().iterator();
        Iterator iterator2 = rate.getAccount().getAddress().iterator();
        AccountInfoDTO accountInfoDTO = null;
        if (iterator1.hasNext()) {
            AccountInfo accountInfo = (AccountInfo) iterator1.next();
            if (iterator2.hasNext()) {
                Address address = (Address) iterator2.next();
                accountInfoDTO = AccountInfoMapper.modelToDto(accountInfo,address.getSub_address());
            } else {
                accountInfoDTO = AccountInfoMapper.modelToDto(accountInfo,"");
            }
        }
        rateDto.setInfo_customer(accountInfoDTO);
        return rateDto;
    }
}
