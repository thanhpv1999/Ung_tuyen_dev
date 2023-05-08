package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ticket_rate")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class TicketRate implements Serializable {
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "ticket_rate_id", columnDefinition = "uniqueidentifier")
	private String ticketRateId;
	@Column(name = "value_of_booking")
	private int valueOfBooking;
	private boolean active;
	@ManyToOne
	@JoinColumn(name = "theatre_id")
	@JsonBackReference
	private Theatres theatres;
	@ManyToOne
	@JoinColumn(name = "seat_id")
//	@JsonBackReference
	private SeatType seatType;
	@ManyToOne
	@JoinColumn(name = "type_ticket_id")
	@JsonBackReference
	private TypeTicket typeTicket;
//	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//	
//    @JoinTable(name = "ticket_rate_price", joinColumns = {@JoinColumn(name = "ticket_rate_id")}, inverseJoinColumns = {@JoinColumn(name = "price_id")})
//    private Set<Price> prices = new HashSet<>();
}
