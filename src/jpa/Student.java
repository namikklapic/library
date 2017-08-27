package jpa;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="student")
public class Student implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	private String brojIndeksa;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private Korisnik korisnik;
	
	private String password;
	private int uspisaniSemestar;
	
	public Student() {}
	
	public Student(Korisnik k, String brojIndeksa, String password, int upisaniSemestar){
		this.korisnik = k;
		this.brojIndeksa = brojIndeksa;
		this.password = password;
		this.uspisaniSemestar = upisaniSemestar;
	}
	
	public Korisnik getKorisnik() {
		return korisnik;
	}
	
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getBrojIndeksa() {
		return brojIndeksa;
	}
	
	public void setBrojIndeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}
	
	public int getUpisaniSemestar() {
		return uspisaniSemestar;
	}
	
	public void setUpisaniSemestar(int uspisaniSemestar) {
		this.uspisaniSemestar = uspisaniSemestar;
	}
}
