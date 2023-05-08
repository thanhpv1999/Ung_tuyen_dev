package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menus")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menus implements Serializable{
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "menus_id" ,columnDefinition = "uniqueidentifier")
	private String menusId;
	@Column(columnDefinition = "nchar(50)")
	private String name;
	@Column(columnDefinition = "nchar(100)")
	private String url;
	@ManyToOne
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@JoinColumn(name = "menu_master", columnDefinition = "uniqueidentifier")
	private Menus menus;

}
