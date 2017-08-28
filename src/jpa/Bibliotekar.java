package jpa;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="bibliotekar")
public class Bibliotekar {

	@OneToOne(cascade=CascadeType.PERSIST)
	@Id
	private Korisnik korisnik;
	private String password;
	@Temporal(TemporalType.DATE)
	private Date loginDatum;
	
	public Bibliotekar(){}
	public Bibliotekar(Korisnik k, String p, Date lD){
		korisnik = k;
		password = p;
		setLoginDatum(lD);
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
	public Date getLoginDatum() {
		return loginDatum;
	}
	public void setLoginDatum(Date loginDatum) {
		this.loginDatum = loginDatum;
	}
		
}
