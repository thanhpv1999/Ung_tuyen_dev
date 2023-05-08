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
@Table(name = "about")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class About implements Serializable {
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "about_id", columnDefinition = "uniqueidentifier")
	private String aboutId;
	@Column(columnDefinition = "nvarchar(500)")
	private String title;
	@Column(columnDefinition = "nvarchar(500)")
	private String description;
	@Column(columnDefinition = "nvarchar(500)")
	private String img;
	@Column(columnDefinition = "nvarchar(500)")
	private String contact;
	
}
