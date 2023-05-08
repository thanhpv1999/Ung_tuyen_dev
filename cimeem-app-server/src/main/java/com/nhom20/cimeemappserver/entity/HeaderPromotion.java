package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "header_promotion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeaderPromotion implements Serializable {
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "id", columnDefinition = "uniqueidentifier")
	private String id;
	@Column(columnDefinition = "nvarchar(100)")
	private String title;
	@Column(columnDefinition = "nvarchar(1000)")
	private String details;
	@Column(name = "expiration_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expirationDate;
	@Column(name = "created_at")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@Transient
	private List<Promotion> promotions;
//	@Transient
//	private boolean status;
	@Column(columnDefinition = "varchar(500)")
	private String img;
	private boolean active=true;

	public HeaderPromotion(String title, String details, Date expirationDate, Date createdAt, String img) {
		super();
		this.title = title;
		this.details = details;
		this.expirationDate = expirationDate;
		this.createdAt = createdAt;
		this.img = img;
	}

}
