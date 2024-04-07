package com.backend.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "[details_product]")
@AllArgsConstructor
@NoArgsConstructor
public class DetailsProduct implements Serializable {

	private static final long serialVersionUID = -3976085812060001794L;

	@Id
	private String id;

	@Column(name = "price")
	private double price;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "status")
	private Boolean status;

	@Column(name = "active")
	private Boolean active;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_size")
	private Size size;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_color")
	private Color color;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pro")
	private Product product;

	@OneToMany(mappedBy = "detailsProduct", cascade = CascadeType.ALL)
	private Set<Cart> cart;

	@OneToMany(mappedBy = "detailsProduct", cascade = CascadeType.ALL)
	private Set<Discount> discount;

	@OneToMany(mappedBy = "detailsProduct", cascade = CascadeType.ALL)
	private Set<Rate> rate;
	
	@OneToMany(mappedBy = "detailsProduct", cascade = CascadeType.ALL)
	private Set<DetailsOrder> detailsOrder;

}
