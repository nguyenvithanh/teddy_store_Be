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
@Table(name = "[Service]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service implements Serializable {

	private static final long serialVersionUID = 8872242168065395449L;

	@Id
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private double price;

	@Column(name = "image")
	private String image;

	@Column(name = "description")
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cate")
	private Category category;
	
	@OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
	private Set<Cart> cart;

	@OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
	private Set<DetailsOrder> detailsOrders;
}
