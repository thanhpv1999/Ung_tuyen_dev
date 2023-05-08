package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@Table(name = "seat_no")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SeatNo implements Serializable {
	private static final long OTP_VALID_DURATION = 5 * 60 * 1000;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "seat_no")
	private Integer seatNo;
	@ManyToOne
	@JoinColumn(name = "seat_id")
	@JsonBackReference
	private Seat seat;
	
	private boolean status;
	@Column(name = "requested_time")
//	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Date requestedTime;
	@ManyToOne
	@JoinColumn(name = "seat_type_id")
//	@JsonBackReference
	private SeatType seatType;
	@Transient
	private List<Object[]> price;
	public boolean setTimeForSeat() {

		long currentTimeInMillis = System.currentTimeMillis();
		long otpRequestedTimeInMillis = this.requestedTime.getTime();
		if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis && requestedTime !=null ) {
			// OTP expires
//			System.out.println(otpRequestedTimeInMillis + OTP_VALID_DURATION);
//			System.out.println(currentTimeInMillis);
			return true;
		}

		return false;
	}
	public String setTimeForSeatString() {

		long currentTimeInMillis = System.currentTimeMillis();
		long otpRequestedTimeInMillis = this.requestedTime.getTime();
		if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis && requestedTime !=null ) {
			// OTP expires
//			System.out.println(otpRequestedTimeInMillis + OTP_VALID_DURATION);
//			System.out.println(currentTimeInMillis);
			return "true";
		}

		return "false";
	}

}
