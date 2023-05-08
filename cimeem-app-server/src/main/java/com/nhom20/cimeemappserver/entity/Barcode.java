package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "barcode")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Barcode implements Serializable{
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "barcode_id" ,columnDefinition = "uniqueidentifier")
	private String barcodeId;
	@Column(name = "img_barcode",columnDefinition = "nvarchar(100)")
	private String imgBarcode;
	@Column(name = "expiration_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expirationDate;
	
	
	
}