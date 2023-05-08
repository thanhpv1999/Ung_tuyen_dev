package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "booking")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Booking implements Serializable {
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "booking_id", columnDefinition = "uniqueidentifier")
	private String bookingId;
	private Boolean status;
	@Column(name = "created_at")
	private String createdAt;
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private Users users;
	@ManyToOne
	@JoinColumn(name = "barcode")
	@JsonBackReference
	private Barcode barcode;
	@ManyToOne
	@JoinColumn(name = "show_time_id")
	@JsonBackReference
	private ShowTimings showTimings;
	@ManyToOne
	@JoinColumn(name = "payment_type_id")
	@JsonBackReference
	private PaymentType paymentType;
	@ManyToOne
	@JoinColumn(name = "theatre_id")
	@JsonBackReference
	private Theatres theatres;
	@ManyToOne
	@JoinColumn(name = "cinema_id")
	@JsonBackReference
	private Cinemas cinemas;
	@ManyToOne
	@JoinColumn(name = "ticket_rate_id")
	@JsonBackReference
	private TicketRate ticketRate;
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JsonBackReference
//	@JoinColumns({
//	  @JoinColumn(name = "theatres", insertable = false, updatable = false),
//	  @JoinColumn(name = "showTimings", insertable = false, updatable = false),
//	  @JoinColumn(name = "room", insertable = false, updatable = false),
//	  @JoinColumn(name = "movie", insertable = false, updatable = false),
//	  @JoinColumn(name = "cinemas", insertable = false, updatable = false)
//	})
////	@JoinColumn(name = "theatre_showtime_id", insertable = false, updatable = false)
//	private TheatreShowTimes theatreShowTimes;
	@ManyToOne
	@JoinColumn(name = "order_id")
	@JsonBackReference
	private Order order;
	
	
}
