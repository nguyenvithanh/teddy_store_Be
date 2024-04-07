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
@Table(name = "[delivery]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery implements Serializable {

	private static final long serialVersionUID = 6653033943670136059L;

	@Id
	private String id;

	@Column(name = "method_shipping")
	private String method_shipping;

	@Column(name = "tracking_no")
	private String tracking_no;

	@Column(name = "status")
	private String status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_ord")
	private Order order;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_acc")
	private Account account;
}
