package com.proiect.management.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name ="angajat")

public class Angajat {
@Id
@Column(name="idE")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int idE;
@Column(name="idM")
    private int idM;
@Column(name="nume")
private String nume;
@Column(name="prenume")
private String prenume;
@Column(name="detali")
private String detali;
@Column(name="adresa")
private String adresa;
@Column(name="username")
private String username;
@Column(name="password")
    private String password;
@Column(name="typeUser")
    private String typeUser;
@Column(name="departament")
private String departament;
@Transient
private List<Angajat> subordinates;

public void add(Angajat e) {
    subordinates.add(e);
 }

 public void remove(Angajat e) {
    subordinates.remove(e);
 }

 public List<Angajat> getSubordinates(){
   return subordinates;
 }
public Angajat() {}



public int getIdE() {
	return idE;
}
public void setIdE(int idE) {
	this.idE = idE;
}
public String getNume() {
	return nume;
}
public void setNume(String nume) {
	this.nume = nume;
}
public String getPrenume() {
	return prenume;
}
public void setPrenume(String prenume) {
	this.prenume = prenume;
}
public String getDetali() {
	return detali;
}
public void setDetali(String detali) {
	this.detali = detali;
}
public String getAdresa() {
	return adresa;
}
public void setAdresa(String adresa) {
	this.adresa = adresa;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getTypeUser() {
	return typeUser;
}
public void setTypeUser(String typeUser) {
	this.typeUser = typeUser;
}
public Angajat(String nume, String prenume, String detali, String adresa, String username, String password,
		String typeUser) {
	super();
	this.nume = nume;
	this.prenume = prenume;
	this.detali = detali;
	this.adresa = adresa;
	this.username = username;
	this.password = password;
	this.typeUser = typeUser;
}
public Angajat(int idE, int idM,String nume, String prenume, String detali, String adresa, String username, String password,
		String typeUser,String dep) {
	super();
	this.idM=idM;
	this.idE = idE;
	this.nume = nume;
	this.prenume = prenume;
	this.detali = detali;
	this.adresa = adresa;
	this.username = username;
	this.password = password;
	this.typeUser = typeUser;
	this.departament = dep;
}

//s.getIdE(), s.getNume(), s.getPrenume(), s.getDepartament(),s.getDetali()};
public Angajat(int idE, int idM,String nume, String prenume, String departament,String detali)
 {
	super();
	this.idM=idM;
	this.idE = idE;
	this.nume = nume;
	this.prenume = prenume;
	this.detali = detali;
	this.departament = departament;

}
public Angajat(int idE)
{
	super();
	this.idE = idE;


}
public int getIdM() {
	return idM;
}

public void setIdM(int idM) {
	this.idM = idM;
}

public String getDepartament() {
	return departament;
}

public void setDepartament(String departament) {
	this.departament = departament;
}

public void setSubordinates(List<Angajat> subordinates) {
	this.subordinates = subordinates;
}

   

}
