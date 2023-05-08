package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.JSONObject;

@Entity
@Table(name = "movie")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Movie implements Serializable {
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "movie_id" ,columnDefinition = "uniqueidentifier")
	private String movieId;
	@Column(columnDefinition = "nvarchar(100)")
	private String title;
	@Column(columnDefinition = "nvarchar(4000)")
	private String description;
	@Column(name = "duration_min")
	private int durationMin;
	@Column(name = "url_trailer",columnDefinition = "varchar(1000)")
	private String urlTrailer;
	@Column(name = "path_img",columnDefinition = "varchar(1000)")
	private String pathImg;
	@Column(name = "start_up_date")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startUpDate;
	private double views=1;
	@Column(name = "votes_avg")
	private float votesAvg=1;
	@Column(name = "votes_count")
	private int votesCount=1;
	@Column(name = "date_aired")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateAired;
	private boolean active;
	@ManyToOne
	@JoinColumn(name = "rate_id")
	private Rate rate;
	@ManyToOne
	@JoinColumn(name = "language_id")
	private Languagess languagess;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "location_movie", joinColumns = {@JoinColumn(name = "movie_id")}, inverseJoinColumns = {@JoinColumn(name = "location_id")})
    private Set<Location> locations = new HashSet<>();
	
	@OneToMany(mappedBy="movie") 
	@JsonIgnore
	private List<TheatreShowTimes> theatreShowTimes;   
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "movie_genres", joinColumns = {@JoinColumn(name = "movie_id")}, inverseJoinColumns = {@JoinColumn(name = "genres_id")})
    private Set<Genres> genres = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "movie_director", joinColumns = {@JoinColumn(name = "movie_id")}, inverseJoinColumns = {@JoinColumn(name = "director_id")})
    private Set<Director> directors = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "movie_cast", joinColumns = {@JoinColumn(name = "movie_id")}, inverseJoinColumns = {@JoinColumn(name = "cast_id")})
    private Set<Cast> casts = new HashSet<>();

}
