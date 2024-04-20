package com.backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDto {
    private String id;
    private String username;
    private boolean active;
    private Date createdAt;
    private AccountInfoDTO info;
}
