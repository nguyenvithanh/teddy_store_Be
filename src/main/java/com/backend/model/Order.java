package com.backend.model;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "[order]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

	private static final long serialVersionUID = -2825611875876500092L;

	@Id
	private String id;

	@Column(name = "date_order")
	private Date date_order;

	@Column(name = "status")
	private String status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_acc")
	private Account account;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private Set<Transactions> transactions;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private Set<Delivery> delivery;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private Set<DetailsOrder> detailsOrder;
}
