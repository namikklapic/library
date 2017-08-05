package jpa;

import javax.persistence.*;

@Entity
@Table(name="nastavnik")
public class Nastavnik {

	@OneToOne(cascade=CascadeType.PERSIST)
	@Id
	private Korisnik korisnik;
	private String password;
	private String akademskoZvanje;
	
	public Nastavnik(){
		
	}
	public Nastavnik(Korisnik k, String zvanje, String p){
		this.korisnik = k;
		this.akademskoZvanje = zvanje;
		this.password = p;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik k) {
		this.korisnik = k;
	}

	public String getAkademskoZvanje() {
		return akademskoZvanje;
	}

	public void setAkademskoZvanje(String akademskoZvanje) {
		this.akademskoZvanje = akademskoZvanje;
	}
	
}
