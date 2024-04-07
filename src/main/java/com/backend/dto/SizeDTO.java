package com.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SizeDTO {
	
	private String id;
	private String size_no;
	private Integer price;
	private Integer price_sale;
	private Integer quantity;
	private Integer count;

	
}
