package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "status")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status implements Serializable{
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "status_id" ,columnDefinition = "uniqueidentifier")
	private String statusId;
	@Enumerated(EnumType.STRING)
	@Column(name="status",columnDefinition = "varchar(50)")
	private StatusOrder name;
	public Status(StatusOrder name) {
		super();
		this.name = name;
	}
	
	
	
}
