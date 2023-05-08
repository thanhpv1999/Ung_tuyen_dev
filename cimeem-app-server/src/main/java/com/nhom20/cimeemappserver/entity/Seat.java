package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "seat")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Seat implements Serializable{

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "seat_id")
	private Integer seatId;
	@Column(name = "row_no" ,columnDefinition = "varchar(10)")
	private String rowNo;
	@ManyToOne
	@JoinColumn(name = "room_id")
	@JsonBackReference
	private Room room;
	@OneToMany(mappedBy="seat") 
	@JsonManagedReference
	private List<SeatNo> seatNos; 
}
