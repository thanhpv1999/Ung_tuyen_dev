package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transaction")
@IdClass(TransactionPK.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable{
	@Id
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	@EmbeddedId
	@ManyToOne
	@JoinColumn(name = "payment_id")
	private Payment payments;
	private double cost;
	private boolean checks;
	private double cash;
	@Column(name = "paid_to",columnDefinition = "varchar(100)")
	private String paidTo;
	@Column(name = "transaction_date")
	private Date transactionDate;
	@Column(name = "transaction_amount")
	private int transactionAmount;
	
}
