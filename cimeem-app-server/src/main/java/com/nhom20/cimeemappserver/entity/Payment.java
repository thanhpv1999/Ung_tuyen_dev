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
@Table(name = "payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable{
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "payment_id" ,columnDefinition = "uniqueidentifier")
	private String paymentId;
	@Column(name = "pay_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date payDate;
//	@Column(name = "update_at")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private Date updateAt;
	private Double total;
	@Column(columnDefinition = "varchar(1000)")
	private Date description;
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private Users users;
	@ManyToOne
	@JoinColumn(name = "type_id")
	@JsonBackReference
	private PaymentType paymentType;
	public String getTotal() {
		// TODO Auto-generated method stub
		return String.format("%.2f",this.total);
	}
	
}
