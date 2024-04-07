package com.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateProDTO {
    private Integer star_no;
	private Integer Number_rate;
	private Integer Quantity; 
}
