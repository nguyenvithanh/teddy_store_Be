package com.backend.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailOrdDTO {
    private Date date;
	private String id;
	private String status;
    private BigDecimal price_unit;
    

    
}
