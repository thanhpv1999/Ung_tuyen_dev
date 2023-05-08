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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_selected_seats")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class UserSelectedSeats implements Serializable {
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "user_selected_seats_id", columnDefinition = "uniqueidentifier")
	private String userSelectedSeatsId;
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private Users users;
	@ManyToOne
	@JoinColumn(name = "theatre_id")
	@JsonBackReference
	private Theatres theatres;
	@ManyToOne
	@JoinColumn(name = "cinema_id")
	@JsonBackReference
	private Cinemas cinemas;
	@ManyToOne
	@JoinColumn(name = "show_time_id")
	@JsonBackReference
	private ShowTimings showTimings;
	@ManyToOne
	@JoinColumn(name = "seat_no")
	@JsonBackReference
	private SeatNo seatNo;
	@ManyToOne
	@JoinColumn(name = "bookings_id")
	@JsonBackReference
	private Booking booking;
	@Column(name = "date_of_show")
	private Date dateOfShow;
	
}
