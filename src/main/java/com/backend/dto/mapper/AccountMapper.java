package com.backend.dto.mapper;

import com.backend.dto.AccountInfoDTO;
import com.backend.dto.CustomerDto;
import com.backend.model.Account;

public class AccountMapper {
    public static CustomerDto toCustomerDto(Account account, AccountInfoDTO info) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(account.getId());
        customerDto.setActive(account.getActive());
        customerDto.setCreatedAt(account.getDate_create());
        customerDto.setInfo(info);

        return customerDto;
    }
}
