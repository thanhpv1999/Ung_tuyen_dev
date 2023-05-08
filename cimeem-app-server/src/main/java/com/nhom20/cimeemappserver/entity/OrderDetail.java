package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_detail_service")
@IdClass(OrderDetailPK.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail implements Serializable{
	@Id
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	@EmbeddedId
	@ManyToOne
	@JoinColumn(name = "orders_service_id")
	private OrderService orders;
	private int amount;
	@Column(name = "created_at")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@Column(name = "updated_at")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	private float discount;
	private double price;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@Cascade(value= {org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name = "status_id")
//	@JsonIgnore 
	private Status status;
	@ManyToOne
	@JoinColumn(name = "theatre_id")
	@JsonIgnore 
	private Theatres theatres;
	@ManyToOne
	@JoinColumn(name = "cinema_id")
	@JsonIgnore 
	private Cinemas cinemas;
	public String getSubtotal() {
		// TODO Auto-generated method stub
		return  String.format("%.2f",this.price*this.amount);
	}

	
}
