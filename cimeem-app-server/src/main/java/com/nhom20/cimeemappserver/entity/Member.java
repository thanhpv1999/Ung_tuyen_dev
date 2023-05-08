package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Member implements Serializable{

	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "group_id" ,columnDefinition = "uniqueidentifier")
	private String groupId;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "nvarchar(1000)")
	private MemberType type;
	private boolean startus=true;
	@Column(name = "expiration_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expirationDate;
	@ManyToOne
	@JoinColumn(name = "barcode")
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.ALL })
	@JsonBackReference
	private Barcode barcode;
}