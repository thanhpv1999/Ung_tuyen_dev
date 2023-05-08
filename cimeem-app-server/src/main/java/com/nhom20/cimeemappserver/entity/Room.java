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
@Table(name = "room")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Room implements Serializable{
	
	@Id
	@Column(name = "room_id")
	private Integer roomId;
	@Column(name = "room_name" ,columnDefinition = "varchar(100)")
	private String roomName;
//	@ManyToOne
//	@JoinColumn(name = "cinema_id")
//	@JsonBackReference
//	private Cinemas cinemas;
	@OneToMany(mappedBy="room") 
	@JsonManagedReference
	private List<Seat> seats; 
	
}
