package com.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailProDTO {
    private String id;
	private String name;
	private String color;
    private String size;
    private Integer purchases;
}


 