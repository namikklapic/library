package jpa;

import javax.persistence.*;

@Entity
@Table(name="nastavnik")
public class Nastavnik {

	@OneToOne
	@Id
	private Korisnik korisnik;
	
	private String akademskoZvanje;
	
	public Nastavnik(){
		
	}
	public Nastavnik(Korisnik k, String zvanje){
		this.korisnik = k;
		this.akademskoZvanje = zvanje;
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
