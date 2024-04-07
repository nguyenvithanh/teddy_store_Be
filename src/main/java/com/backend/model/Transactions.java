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
@Table(name = "[transactions]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transactions implements Serializable{

	private static final long serialVersionUID = -7519287649507701260L;

	@Id
	private String id;
	
	@Column(name="vnp_amount")
	private double vnp_amount;
	
	@Column(name="vnp_bank_Tran_No")
	private String vnp_bankTranNo;

	@Column(name="vnp_card_Type")
	private String vnp_cardType;

	@Column(name="vnp_order_Info")
	private String vnp_orderInfo;

	@Column(name="vnp_response_Code")
	private String vnp_responseCode;

	@Column(name="vnp_tmn_Code")
	private String vnp_tmnCode;

	@Column(name="vnp_transaction_No")
	private String vnp_transactionNo;

	@Column(name="vnp_transaction_Status")
	private String vnp_transactionStatus;

	@Column(name="vnp_txn_Ref")
	private String vnp_txnRef;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_ord")
	private Order order;
}
