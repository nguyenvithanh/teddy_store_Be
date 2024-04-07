package com.backend.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "[product_image]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage implements Serializable{
	
	private static final long serialVersionUID = 3265876643530802173L;

	@Id
	private String id;
	
	@Column(name="img_url")
	private String img_url;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pro")
	private Product product;
}
