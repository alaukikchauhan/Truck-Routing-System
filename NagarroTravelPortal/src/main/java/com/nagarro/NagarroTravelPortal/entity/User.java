package com.nagarro.NagarroTravelPortal.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@DynamicUpdate
public class User implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String fname;
	
	@NotNull
	private String lname;
	
	@NotNull
	private String bunit;
	
	@NotNull
	private String title;
	
	@NotNull
	@NaturalId
	@Column(unique = true)
	private String email;
	
	@NotNull
	private String add1;
	
	private String add2;
	
	@NotNull
	private String city;
	
	@NotNull
	private String state;
	
	@NotNull
	private Integer zcode;
	
	@NotNull
	private String country;

	@NotNull
//    @ValidPassword
	private String password;
	
	@NotNull
	private Long tel; 

		public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getBunit() {
		return bunit;
	}

	public void setBunit(String bunit) {
		this.bunit = bunit;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	
	public String getAdd1() {
		return add1;
	}

	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	public String getAdd2() {
		return add2;
	}

	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	public Long getTel() {
		return tel;
	}

	public void setTel(Long tel) {
		this.tel = tel;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {}

	
	
	

	public void copy(User user)
	{
		this.fname = user.fname;
		this.lname = user.lname;
		this.bunit = user.bunit;
		this.title = user.title;
		this.email = user.email;
		this.add1 = user.add1;
		this.add2 = user.add2;
		this.city = user.city;
		this.state = user.state;
		this.zcode =user.zcode;
		this.country = user.country;
		this.password=user.password;
		this.tel=user.tel;
	}

	public Integer getZcode() {
		return zcode;
	}

	public void setZcode(Integer zcode) {
		this.zcode = zcode;
	}

	public User(@NotNull String fname, @NotNull String lname, @NotNull String bunit, @NotNull String title,
			@NotNull String email, @NotNull String add1, String add2, @NotNull String city, @NotNull String state,
			@NotNull Integer zcode, @NotNull String country, @NotNull Long tel) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.bunit = bunit;
		this.title = title;
		this.email = email;
		this.add1 = add1;
		this.add2 = add2;
		this.city = city;
		this.state = state;
		this.zcode = zcode;
		this.country = country;
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "User [fname=" + fname + ", lname=" + lname + ", bunit=" + bunit + ", title=" + title + ", email="
				+ email + ", add1=" + add1 + ", add2=" + add2 + ", city=" + city + ", state=" + state + ", zcode="
				+ zcode + ", country=" + country + ", password=" + password + ", tel=" + tel + "]";
	}
	
	
	
}

