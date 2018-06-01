package com.proiect.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="salariu")
public class Salariu {
	@Id
	@Column(name="idSal")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSal;
	@Column(name="idE")
	private int idE;
	@Column(name="salariu")
	private int salariu;
	public Salariu(int idE, int salariu) {
		super();
		this.idE = idE;
		this.salariu = salariu;
	}
	public Salariu(int idSal, int idE, int salariu) {
		super();
		this.idSal = idSal;
		this.idE = idE;
		this.salariu = salariu;
	}
	public int getIdSal() {
		return idSal;
	}
	public void setIdSal(int idSal) {
		this.idSal = idSal;
	}
	public int getIdE() {
		return idE;
	}
	public void setIdE(int idE) {
		this.idE = idE;
	}
	public int getSalariu() {
		return salariu;
	}
	public void setSalariu(int salariu) {
		this.salariu = salariu;
	}

}
