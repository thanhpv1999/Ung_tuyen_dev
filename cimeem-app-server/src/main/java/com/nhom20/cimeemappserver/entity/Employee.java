package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Employee implements Serializable {
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "employee_id", columnDefinition = "uniqueidentifier")
	private String employeeId;
	@Column(name = "first_Name", columnDefinition = "varchar(100)")
	private String firstName;
	@Column(name = "last_Name", columnDefinition = "varchar(100)")
	private String lastName;
	@Column(columnDefinition = "varchar(100)")
	private String email;
	private boolean sex;
	@Column( columnDefinition = "varchar(100)")
	private String avatar;
	private boolean active;
	@Column(columnDefinition = "varchar(100)")
	private String phone;
	@ManyToOne
	@JoinColumn(name = "address_id")
//	@JsonBackReference
	private Address addresss;
	@ManyToOne
	@JoinColumn(name = "theatre_id")
//	@JsonBackReference
	private Theatres theatres;
	@Column(columnDefinition = "varchar(1000)")
	private String password;
	@ManyToOne
	@JoinColumn(name = "jop_type")
	@JsonBackReference
	private JopType jopType;
	@Column(name = "hire_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hireDate;
	
	@ManyToOne
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@JoinColumn(name = "manager_id", columnDefinition = "uniqueidentifier")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Users users;
	
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "role_employee", joinColumns = {@JoinColumn(name = "employee_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Roles> roles = new HashSet<>();
	public Employee(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

}
