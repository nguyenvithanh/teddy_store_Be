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
@Table(name = "[rate]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rate implements Serializable{
	
	private static final long serialVersionUID = 1136948060526304918L;

	@Id
	private String id;
	
	@Column(name="star_no")
	private Integer star_no;

	@Column(name="description")
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_dt_pro")
	private DetailsProduct detailsProduct;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_acc")
	private Account account;
}
