package com.backend.model;

import java.io.Serial;
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
@Table(name = "[Cart]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {

	@Serial
	private static final long serialVersionUID = -6689334928223129869L;

	@Id
	private String id;

	@Column(name = "quantity_pro")
	private Integer quantity_pro;

	@Column(name = "quantity_ser")
	private Integer quantity_ser;

	@Column(name = "date_add")
	private Date date_add;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_ser",referencedColumnName = "id")
	private Service service;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_dt_pro",referencedColumnName = "id")
	private DetailsProduct detailsProduct;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_acc",referencedColumnName = "id")
	private Account account;
	
}