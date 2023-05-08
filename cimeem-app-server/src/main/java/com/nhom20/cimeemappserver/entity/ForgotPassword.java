package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.UUID;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "forgot_password")
public class ForgotPassword implements Serializable{
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "id",columnDefinition = "uniqueidentifier")
	private String id;
	private String value;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ForgotPassword(String id) {
		super();
		this.id = id;
	}

	public ForgotPassword() {
		super();
	}

	@Override
	public String toString() {
		return "ForgotPassword [id=" + id + "]";
	}
	
}
