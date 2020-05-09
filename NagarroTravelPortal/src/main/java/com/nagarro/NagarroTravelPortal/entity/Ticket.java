package com.nagarro.NagarroTravelPortal.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@DynamicUpdate
public class Ticket implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
 	
	@NotNull
	private String rtype;
	
	@NotNull
	private String tcity;
	
	@NotNull
	private String lcity;
	
	@NotNull
	private String sdate;
	
	@NotNull
	private String edate;
	
	@NotNull
	private String pno;
	
	@NotNull
	private String pname;
	
	@NotNull
	private int radio1;
	
	private String approver;
	
	private int duration;
	
	private int uamount;
	
	@NotNull
	private String details;
	
	@NotNull
	private String email;

	Ticket (){}

	public Ticket(@NotNull String rtype, @NotNull String tcity, @NotNull String lcity, @NotNull String sdate,
			@NotNull String edate, @NotNull String pno, @NotNull String pname, @NotNull int radio1, String approver,
			int duration, int uamount, @NotNull String details) {
		super();
		this.rtype = rtype;
		this.tcity = tcity;
		this.lcity = lcity;
		this.sdate = sdate;
		this.edate = edate;
		this.pno = pno;
		this.pname = pname;
		this.radio1 = radio1;
		this.approver = approver;
		this.duration = duration;
		this.uamount = uamount;
		this.details = details;
	}

	public String getRtype() {
		return rtype;
	}

	public void setRtype(String rtype) {
		this.rtype = rtype;
	}

	public String getTcity() {
		return tcity;
	}

	public void setTcity(String tcity) {
		this.tcity = tcity;
	}

	public String getLcity() {
		return lcity;
	}

	public void setLcity(String lcity) {
		this.lcity = lcity;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public String getPno() {
		return pno;
	}

	public void setPno(String pno) {
		this.pno = pno;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getRadio1() {
		return radio1;
	}

	public void setRadio1(int radio1) {
		this.radio1 = radio1;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getUamount() {
		return uamount;
	}

	public void setUamount(int uamount) {
		this.uamount = uamount;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return Id;
	}

	@Override
	public String toString() {
		return "Ticket [Id=" + Id + ", rtype=" + rtype + ", tcity=" + tcity + ", lcity=" + lcity + ", sdate=" + sdate
				+ ", edate=" + edate + ", pno=" + pno + ", pname=" + pname + ", radio1=" + radio1 + ", approver="
				+ approver + ", duration=" + duration + ", uamount=" + uamount + ", details=" + details + "]";
	}
	
	
}