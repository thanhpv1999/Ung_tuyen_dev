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
import javax.persistence.Transient;

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
@Table(name = "theatres")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Theatres implements Serializable{

	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "theatre_id" ,columnDefinition = "uniqueidentifier")
	private String theatreId;
	@Column(name = "theatre_name",columnDefinition = "varchar(50)")
	private String theatreName;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	@JsonBackReference
	private City city;
	private double latitude;
	private double longtitude;
	@Transient
	private List<Cinemas> cinemas;
	@Transient
	private List<Room> room;
}
