package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cast")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Cast implements Serializable {
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "cast_id", columnDefinition = "uniqueidentifier")
	private String castId;
	@Column(columnDefinition = "nvarchar(100)")
	private String name;
	@Column(name = "character_name",columnDefinition = "nvarchar(100)")
	private String characterName;
	@Column(name = "cast_order",columnDefinition = "nvarchar(100)")
	private String castOrder;
	
}
