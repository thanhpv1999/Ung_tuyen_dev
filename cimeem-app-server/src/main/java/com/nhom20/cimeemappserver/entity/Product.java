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
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "product_id", columnDefinition = "uniqueidentifier")
	private String productId;
	
	@GeneratedValue(generator = "my_generator")
	@GenericGenerator(name = "my_generator", strategy = "com.nhom20.cimeemappserver.entity.MyGenerator")
	private String id;
	@Column(columnDefinition = "nvarchar(100)")
	private String name;
	private int amount;
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "product_type")
	private ProductType productType;
	@Column(columnDefinition = "nvarchar(1000)")
	private String description;
	@Column(columnDefinition = "varchar(100)")
	private String img;
//	@OneToMany(mappedBy="product") 
////	@JsonManagedReference
//	private List<ProductPrice> productPrices; 
	@Transient
	private double price;
	@Transient
	private List<Price> prices;

	public Product(String name, int amount, ProductType productType, String description, String img) {
		super();
		this.name = name;
		this.amount = amount;
		this.productType = productType;
		this.description = description;
		this.img = img;
	}

}
