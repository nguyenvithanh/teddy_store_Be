package com.backend.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "[color]")
@NoArgsConstructor
@AllArgsConstructor
public class Color implements Serializable {

	private static final long serialVersionUID = 1128002585646130548L;

	@Id
	private String id;

	@Column(name = "color")
	private String color;

	@OneToMany(mappedBy = "color", cascade = CascadeType.ALL)
	private Set<DetailsProduct> detailsProduct;
}
