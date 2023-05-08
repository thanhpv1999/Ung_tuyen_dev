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
@Table(name = "type_ticket")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class TypeTicket implements Serializable {
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "type_ticket_id", columnDefinition = "uniqueidentifier")
	private String typeTicketId;
	@Column(columnDefinition = "varchar(100)")
	private String type;
	@Column(columnDefinition = "varchar(1000)")
	private String details;
	
}
