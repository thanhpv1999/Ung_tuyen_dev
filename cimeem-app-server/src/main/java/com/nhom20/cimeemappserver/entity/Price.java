package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "price")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Price implements Serializable {
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "price_id", columnDefinition = "uniqueidentifier")
	private String priceId;
	@Column(columnDefinition = "varchar(100)")
	private String decs;
	@Column(name="currency_unit",columnDefinition = "varchar(10)")
	private String currencyUnit;
	@Column(columnDefinition = "varchar(100)")
	private String title;
	@Column(name = "start_day")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDay;
	@Column(name = "end_day")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDay;
	private double value;
	private boolean active;
	@Column(name="import_price")
	private double importPrice;
	@Column(name="retail_price")
	private double retailPrice;
	@Transient
	private List<Product> products;
	@Transient
	private List<TicketRate> ticketRates;
	@Transient
	private List<SeatType> seatTypes;
	
	
}
