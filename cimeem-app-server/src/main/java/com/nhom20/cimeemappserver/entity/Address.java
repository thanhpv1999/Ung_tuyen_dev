package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Address implements Serializable{

	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "address_id" ,columnDefinition = "uniqueidentifier")
	private String addressId;
	@Column(columnDefinition = "varchar(50)")
	private String address;
	@Column(columnDefinition = "varchar(50)")
	private String address2;
	@Column(name = "postal_code",columnDefinition = "varchar(10)")
	private String postalCode;
	@Column(name = "phone",columnDefinition = "varchar(20)")
	private String phone;
	@Column(name = "last_update")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastUpdate;
//	@OneToMany(mappedBy="addresss") 
//	@JsonManagedReference
//	private List<Users> users;   
//	@OneToMany(mappedBy="shipAddress") 
//	@JsonManagedReference
//	private List<Orders> orders;   
	@ManyToOne
	@JoinColumn(name = "city_id")
//	@JsonBackReference
	private City city;
}
