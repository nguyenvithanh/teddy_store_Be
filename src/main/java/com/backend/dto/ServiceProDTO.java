package com.backend.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceProDTO {
	
    private String name;
    private BigDecimal priceSv;
    private Integer product_count;
    private String id;

}
