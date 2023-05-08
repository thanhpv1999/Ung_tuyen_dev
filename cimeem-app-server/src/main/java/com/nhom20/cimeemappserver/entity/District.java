package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "district")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class District implements Serializable{
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "district_id" ,columnDefinition = "uniqueidentifier")
	private String districtId;
	@Column(columnDefinition = "nvarchar(100)")
	private String district;
	@Column(name = "last_update")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date lastUpdate;
	@Column(columnDefinition = "varchar(100)")
	private String type;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.ALL })
	@JoinColumn(name = "city_id")
	@JsonBackReference
	private City city;
	@OneToMany(mappedBy="district") 
//	@JsonManagedReference
	private List<Ward> wards;  
	
	
}
