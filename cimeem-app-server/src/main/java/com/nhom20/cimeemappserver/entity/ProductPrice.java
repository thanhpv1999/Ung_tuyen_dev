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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_price")
@IdClass(ProductPricePk.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPrice implements Serializable{
	@EmbeddedId
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "product_id")
	private Product product;
	@EmbeddedId
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "price_id")
	private Price price;
	private boolean active;
	
	
}
