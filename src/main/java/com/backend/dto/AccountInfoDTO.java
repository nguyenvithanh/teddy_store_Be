package com.backend.dto;
 
import lombok.Data;

import java.util.Date;

@Data
public class AccountInfoDTO {

    private String id;

    private String name;

    private String avatar;

    private String email;

    private String address;

    private Integer phone;

    private Date birthday;

    private Boolean gender;
}
