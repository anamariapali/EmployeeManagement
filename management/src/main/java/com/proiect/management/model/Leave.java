package com.proiect.management.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="eleave")
public class Leave {
	@Id
	@Column(name="idLeave")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLeave;
	@Column(name="idE")
	private int idE;
	@Column(name="startDate")
	private Date startDate;
	@Column(name="endDate")
	private Date endDate;
	@Column(name="satatusLeave")
	private Date statusLeave;
	public int getIdLeave() {
		return idLeave;
	}
	public void setIdLeave(int idLeave) {
		this.idLeave = idLeave;
	}
	public int getIdE() {
		return idE;
	}
	public void setIdE(int idE) {
		this.idE = idE;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getStatusLeave() {
		return statusLeave;
	}
	public void setStatusLeave(Date statusLeave) {
		this.statusLeave = statusLeave;
	}
	public Leave(int idE, Date startDate, Date endDate, Date statusLeave) {
		super();
		this.idE = idE;
		this.startDate = startDate;
		this.endDate = endDate;
		this.statusLeave = statusLeave;
	}
	public Leave(int idLeave, int idE, Date startDate, Date endDate, Date statusLeave) {
		super();
		this.idLeave = idLeave;
		this.idE = idE;
		this.startDate = startDate;
		this.endDate = endDate;
		this.statusLeave = statusLeave;
	}
	
	

}
