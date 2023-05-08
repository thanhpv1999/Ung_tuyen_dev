package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "promotion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Promotion implements Serializable{
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(columnDefinition = "uniqueidentifier")
	private String id;
	@Column(columnDefinition = "varchar(100)")
	private String code;
	@Column(name = "discount_amount")
	private int discountAmount;
	@Column(name = "expiration_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expirationDate;
	@Column(columnDefinition = "nvarchar(1000)")
	private String details;
	private float discount;
	@Column(columnDefinition = "nvarchar(100)")
	private String status;
	@OneToMany(mappedBy="promotion") 
//	@JsonManagedReference
	private List<CouponRedemption> couponRedemptions; 
	@ManyToOne
	@JoinColumn(name = "type")
//	@JsonBackReference
	private TypePromotion typePromotion;
	
	
	
}
