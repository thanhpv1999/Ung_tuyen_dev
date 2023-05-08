package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coupon_redemption")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponRedemption implements Serializable{
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(columnDefinition = "uniqueidentifier")
	private String id;
	@Column(name = "redemption_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date redemptionDate;
	@Column(name = "total_discount")
	private int totalDiscount;
	@JoinColumn(name = "amount_discount")
	private int amountDiscount;
	
	@ManyToOne
	@JoinColumn(name = "user_type")
	@JsonBackReference
	private Users users;
	@ManyToOne
	@JoinColumn(name = "code")
	@JsonBackReference
	private Promotion promotion;
	@ManyToOne
	@JoinColumn(name = "product_id")
//	@JsonBackReference
	private Product product;
	@ManyToOne
	@JoinColumn(name = "seat_type")
//	@JsonBackReference
	private SeatType seatType;
}
