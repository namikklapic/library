package jpa;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="bibliotekar")
public class Bibliotekar {

	@OneToOne(cascade=CascadeType.PERSIST)
	@Id
	private Korisnik korisnik;
	private String password;
	
	public Bibliotekar(){}
	public Bibliotekar(Korisnik k, String p){
		korisnik = k;
		password = p;
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
		
}
