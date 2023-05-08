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
@Table(name = "review")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review implements Serializable{
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "review_id" ,columnDefinition = "uniqueidentifier")
	private String reviewId;
	@Column(columnDefinition = "nchar(1000)")
	private String comment;
	private int status;
	@Column(name = "count_like")
	private int countLike;
	@ManyToOne
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@JoinColumn(name = "user_id", columnDefinition = "uniqueidentifier")
	private Users users;
	@ManyToOne
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@JoinColumn(name = "movie_id", columnDefinition = "uniqueidentifier")
	private Movie movie;
	@ManyToOne
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@JoinColumn(name = "review_reply", columnDefinition = "uniqueidentifier")
	private Review review;
	
}
