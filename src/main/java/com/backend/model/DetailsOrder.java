package com.backend.model;

import java.io.Serial;
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
@Table(name = "[details_order]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailsOrder implements Serializable{
	
	@Serial
	private static final long serialVersionUID = 3165204643475068723L;

	@Id
	private String id;
	
	@Column(name="quantity_pro")
	private Integer quantity_pro;
	
	@Column(name="price_unit")
	private Double price_unit;

	@Column(name="quantity_ser")
	private Integer quantity_ser;

	@Column(name="price_unit_ser")
	private Double price_unit_ser;
	
	@Column(name="address")
	private String address;
	
	@Column(name="note")
	private String note;
	
	@Column(name="method_payment")
	private String method_payment;

	@Column(name="amount")
	private Double amount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_dt_pro")
	private DetailsProduct detailsProduct;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_ser")
	private Service service;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_ord")
	private Order order;
}
