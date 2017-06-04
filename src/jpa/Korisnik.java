package jpa;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="korisnik")
public class Korisnik implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String sifra;
	
	private String imeKorisnika;
	private String prezimeKorisnika;
	private int brojNegativnihBodova;
	
	public Korisnik(){
		
	}
	
	public Korisnik(String sifra, String ime, String prezime, int brojBodova){
		this.sifra = sifra;
		this.imeKorisnika = ime;
		this.prezimeKorisnika = prezime;
		this.brojNegativnihBodova = brojBodova;
	}

	public String getSifraKorisnika() {
		return sifra;
	}

	public void setSifraKorisnika(String sifraKorisnika) {
		this.sifra = sifraKorisnika;
	}

	public String getImeKorisnika() {
		return imeKorisnika;
	}

	public void setImeKorisnika(String imeKorisnika) {
		this.imeKorisnika = imeKorisnika;
	}

	public String getPrezimeKorisnika() {
		return prezimeKorisnika;
	}

	public void setPrezimeKorisnika(String prezimeKorisnika) {
		this.prezimeKorisnika = prezimeKorisnika;
	}

	public int getBrojNegativnihBodova() {
		return brojNegativnihBodova;
	}

	public void setBrojNegativnihBodova(int brojNegativnihBodova) {
		this.brojNegativnihBodova = brojNegativnihBodova;
	}
	
	
	
}
