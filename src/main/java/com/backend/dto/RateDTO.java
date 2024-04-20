package com.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateDTO {
    String id;
    String id_product;
    Integer star;
    String description;
    AccountInfoDTO info_customer;
}
