package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "type_promotion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypePromotion implements Serializable{
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(columnDefinition = "uniqueidentifier")
	private String id;
	@Column(columnDefinition = "varchar(50)")
	private String type;
	@Column(columnDefinition = "varchar(10)")
	private String details;
	
}
