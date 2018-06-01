package com.proiect.management.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="tasks")
public class Task {
	@Id
	@Column(name="idTask")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTask;
	@Column(name="idE")
    private int idE;
	@Column(name="idEfrom")
    private int idEfrom;
	@Column(name="dueDate")
	private Date dueDate;
	@Column(name="statusTask")
	private String statusTask;
	public int getIdTask() {
		return idTask;
	}
	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}
	public int getIdE() {
		return idE;
	}
	public void setIdE(int idE) {
		this.idE = idE;
	}
	public int getIdEfrom() {
		return idEfrom;
	}
	public void setIdEfrom(int idEfrom) {
		this.idEfrom = idEfrom;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getStatusTask() {
		return statusTask;
	}
	public void setStatusTask(String statusTask) {
		this.statusTask = statusTask;
	}
	public Task(int id,int idE, int idEfrom, Date dueDate, String statusTask) {
		super();
		this.idTask=id;
		this.idE = idE;
		this.idEfrom = idEfrom;
		this.dueDate = dueDate;
		this.statusTask = statusTask;
	}
	public Task( int idE,int idEfrom, Date dueDate, String statusTask) {
		super();
		this.idE = idE;
		this.idEfrom = idEfrom;
		this.dueDate = dueDate;
		this.statusTask = statusTask;
	}
	public Task() {}
	public Task(int idAngajat, Date dueDate2, String status) {
		// TODO Auto-generated constructor stub
		this.idEfrom = idAngajat;
		this.dueDate = dueDate2;
		this.statusTask = status;
	}
	
	
}
