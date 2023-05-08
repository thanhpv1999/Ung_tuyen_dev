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
@Table(name = "account_users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Users implements Serializable {
	private static final long OTP_VALID_DURATION = 5 * 60 * 1000;
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "user_id", columnDefinition = "uniqueidentifier")
	private String userId;
	@Column(name = "first_name", columnDefinition = "varchar(100)")
	private String firstName;
	@Column(name = "last_name", columnDefinition = "varchar(100)")
	private String lastName;
	@Column(columnDefinition = "varchar(100)")
	private String email;
	private boolean sex;
	@Column(name = "avatar", columnDefinition = "varchar(100)")
	private String avatar;
	private boolean active;
	@Column(name = "birth_day")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDay;
	private int star;
	private double expense;
	@Column(name = "one_time_password", columnDefinition = "varchar(1000)")
	private String oneTimePassword;
	@Column(name = "otp_requested_time")
//	@DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss a")
	private Date otpRequestedTime;
	@Column(columnDefinition = "varchar(500)")
	private String phone;
	
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "role_user", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Roles> roles = new HashSet<>();
	@ManyToOne
	@JoinColumn(name = "address_id")
//	@JsonBackReference
	private Address addresss;
	@OneToMany(mappedBy = "users")
	@JsonIgnore
	private List<CouponRedemption> couponRedemptions;
	@OneToMany(mappedBy = "users")
	@JsonIgnore
	private List<CustomerPassword> customerPasswords;
	@OneToMany(mappedBy = "user")
//	@JsonIgnore
	private List<MemberUser> memberUsers ;
	public Users(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public boolean isOTPRequired() {
		if (this.getOneTimePassword() == null) {
			return false;
		}

		long currentTimeInMillis = System.currentTimeMillis();
		long otpRequestedTimeInMillis = this.otpRequestedTime.getTime();

		if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis) {
			// OTP expires
			return false;
		}

		return true;
	}

}
