package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "member_users")
@IdClass(MemberUserPk.class)
@Data
@AllArgsConstructor
@NoArgsConstructor

public class MemberUser implements Serializable{
	@EmbeddedId
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "user_id")
	private Users user;
	@EmbeddedId
	@ManyToOne
//	@JsonBackReference
	@JoinColumn(name = "group_id")
	private Member member;
	@Column(name = "participation_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date participationDate;

}
