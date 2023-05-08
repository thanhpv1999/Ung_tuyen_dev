package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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

@Embeddable

public class TheatreShowTimesPk implements Serializable {

	private String theatres;

	private String showTimings;

	private Integer room;
	
	private String cinemas;

	@Override
	public int hashCode() {
		return Objects.hash(cinemas, room, showTimings, theatres);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TheatreShowTimesPk other = (TheatreShowTimesPk) obj;
		return Objects.equals(cinemas, other.cinemas) && Objects.equals(room, other.room)
				&& Objects.equals(showTimings, other.showTimings) && Objects.equals(theatres, other.theatres);
	}


	

}
