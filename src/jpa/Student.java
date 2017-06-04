package jpa;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="student")
public class Student implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@Id
	private Korisnik korisnik;
	
	private String brojIndeksa;
	private int uspisaniSemestar;
	
	public Student(){
		
	}
	public Student(Korisnik k, String brojIndeksa, int upisaniSemestar){
		this.korisnik = k;
		this.brojIndeksa = brojIndeksa;
		this.uspisaniSemestar = upisaniSemestar;
	}
	
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public String getBrojIndeksa() {
		return brojIndeksa;
	}
	public void setBrojIndeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}
	public int getUspisaniSemestar() {
		return uspisaniSemestar;
	}
	public void setUspisaniSemestar(int uspisaniSemestar) {
		this.uspisaniSemestar = uspisaniSemestar;
	}
	
	

}
