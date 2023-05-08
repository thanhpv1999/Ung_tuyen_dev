package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "theatre_show_times")
@IdClass(TheatreShowTimesPk.class)
@Data
@AllArgsConstructor
@NoArgsConstructor

public class TheatreShowTimes implements Serializable {
	@EmbeddedId
	@ManyToOne
	@JoinColumn(name = "theatre_id")
	private Theatres theatres;
	
	@EmbeddedId
	@ManyToOne
	@JoinColumn(name = "show_time_id")
	private ShowTimings showTimings;
	
	@EmbeddedId
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;

	
	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
	@EmbeddedId
	@ManyToOne
	@JoinColumn(name = "cinema_id")
	private Cinemas cinemas;
	
	@Column(name = "date_starts_from")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateStartsFrom;
	
	@Column(name = "date_ends")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateEnds;
	
	private boolean status;

}
