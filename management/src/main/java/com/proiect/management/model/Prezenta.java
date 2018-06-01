package com.proiect.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="prezenta")
public class Prezenta {
	@Id
	@Column(name="idPrezenta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPrezenta;
	@Column(name="idE")
	private int idE;
	@Column(name="nr")
	private int nr;
	@Column(name="comentariu")
	private String comentariu;
	public int getIdPrezenta() {
		return idPrezenta;
	}
	public void setIdPrezenta(int idPrezenta) {
		this.idPrezenta = idPrezenta;
	}
	public int getIdE() {
		return idE;
	}
	public void setIdE(int idE) {
		this.idE = idE;
	}
	public int getNr() {
		return nr;
	}
	public void setNr(int nr) {
		this.nr = nr;
	}
	public String getComentariu() {
		return comentariu;
	}
	public void setComentariu(String comentariu) {
		this.comentariu = comentariu;
	}
	public Prezenta(int idE, int nr, String comentariu) {
		super();
		this.idE = idE;
		this.nr = nr;
		this.comentariu = comentariu;
	}
	public Prezenta(int idPrezenta, int idE, int nr, String comentariu) {
		super();
		this.idPrezenta = idPrezenta;
		this.idE = idE;
		this.nr = nr;
		this.comentariu = comentariu;
	}
	public Prezenta(){}
	
}
