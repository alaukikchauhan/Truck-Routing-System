package com.example.ProfitabilityEnahancement.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@DynamicUpdate
public class Truck 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Float lat;
	
	private Float longi;

	private Integer code;
	
	private String name;
	
	private String address;
	
	private Long mobile;
	
	private String email;
	
	public Float getLat() {
		return lat;
	}


	public void setLat(Float lat) {
		this.lat = lat;
	}


	public Float getLongi() {
		return longi;
	}


	public void setLongi(Float longi) {
		this.longi = longi;
	}


	public Integer getCode() {
		return code;
	}


	public void setCode(Integer code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Long getMobile() {
		return mobile;
	}


	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Truck [id=" + id + ", lat=" + lat + ", longi=" + longi + ", code=" + code + ", name=" + name
				+ ", address=" + address + ", mobile=" + mobile + ", email=" + email + "]";
	}


	
	
	public Truck(Float lat, Float longi, Integer code, String name, String address, Long mobile, String email) {
		super();
		this.lat = lat;
		this.longi = longi;
		this.code = code;
		this.name = name;
		this.address = address;
		this.mobile = mobile;
		this.email = email;
	}


	public Truck (){}
	
}
