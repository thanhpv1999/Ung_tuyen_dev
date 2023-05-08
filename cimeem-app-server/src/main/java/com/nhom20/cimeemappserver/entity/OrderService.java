package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;
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
@Table(name = "orders_service")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderService implements Serializable {

	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "orders_service_id", columnDefinition = "uniqueidentifier")
	private String ordersServiceId;
	@Column(name = "created_at")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@Column(name = "updated_at")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	@JsonBackReference
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.ALL })
	private Users users;
//	status_id
	@ManyToOne(cascade = CascadeType.PERSIST)
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.ALL })
	@JoinColumn(name = "status_id")
	@JsonBackReference
	private Status status;
	@ManyToOne
	@JoinColumn(name = "barcode")
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.ALL })
	@JsonBackReference
	private Barcode barcode;
	@ManyToOne
	@JoinColumn(name = "payment_type_id")
	@JsonBackReference
	private PaymentType paymentType;
//	@ManyToOne
//	@JoinColumn(name = "order_id")
//	@JsonBackReference
//	private Order order;
	public OrderService(Date created_at, Date updatedAt, Users users,
			Status status) {
		super();
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.users = users;
		this.status = status;
	}

	
}
