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
@Table(name = "ward")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ward implements Serializable{
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "ward_id" ,columnDefinition = "uniqueidentifier")
	private String wardId;
	@Column(columnDefinition = "nvarchar(100)")
	private String ward;
	@Column(name = "last_update")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastUpdate;
	@Column(columnDefinition = "varchar(100)")
	private String type;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.ALL })
	@JoinColumn(name = "district_id")
	@JsonBackReference
	private District district;
	
	
}
