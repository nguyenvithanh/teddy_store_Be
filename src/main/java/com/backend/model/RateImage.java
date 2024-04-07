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
@Table(name = "[rate_image]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateImage implements Serializable{

	private static final long serialVersionUID = -4397245523746460153L;

	@Id
	private String id;
	
	@Column(name="url")
	private String url;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_rate")
	private Rate rate;
}
