package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import groovy.transform.ToString;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "customer_password")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class CustomerPassword implements Serializable{

	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "password_id" ,columnDefinition = "uniqueidentifier")
	private String passwordId;
	@Column(columnDefinition = "varchar(1000)")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@Column(name = "created_at")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@Column(name = "updated_at")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private Users users;
}
