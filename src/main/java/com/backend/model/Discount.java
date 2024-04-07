package com.backend.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "[discount]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discount implements Serializable{

	private static final long serialVersionUID = -7483738184787165428L;
	
	@Id
	private String id;
	
	@Column(name="price_sale")
	private Double price_sale;
	
	@Column(name="date_start")
	private Date date_start;
	
	@Column(name="date_end")
	private Date date_end;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_dt_pro")
	private DetailsProduct detailsProduct;

}
