package jpa;

import javax.persistence.*;
/*
 * Persistence klasa za kreiranje tabele knjiga
 * Klasa ima sljedece atribute:
 * 		id - koji je primarni kljuc i sam se generira;
 * 		naslov - naslov knjige
 * 		originalniNaslov
 * 		brojStranica
 * 		godinaIzdavanja
 * 		negBodovi koji se dodaju korisniku ako kasni sa vracanjem knjige
 * 		
 * 		Knjiga se spaja sa autorom, izdavacem, vrstom knjige, i primjerkom
 * 
 * 
 */
@Entity
@Table(name="knjiga")
public class Knjiga {
	@Id
	private int id;
	
	private String naslov;
	private String originalniNaslov;
	private int brojStranica;
	private int godinaIzdavanja;
	private int negBodovi;
	private int brojPrimjeraka;
	
	@ManyToOne
	private VrstaKnjige vrsta;
	
	@ManyToOne
	private Izdavac izdavac;
	
	
	public Knjiga() {}
	
	public Knjiga(int id, String naslov, String orgNaslov, int brojStranica, int godinaIzdavanja,
			int negBodovi, int brojPrimjeraka, Izdavac izdavac, VrstaKnjige vrsta){
		this.id = id;
		this.naslov = naslov;
		this.originalniNaslov = orgNaslov;
		this.brojStranica = brojStranica;
		this.godinaIzdavanja = godinaIzdavanja;
		this.negBodovi = negBodovi;
		this.brojPrimjeraka = brojPrimjeraka;
		this.izdavac = izdavac;
		this.vrsta = vrsta;
	}
	
	public int getId(){
		return id;
	}
	
	public VrstaKnjige getVrsta() {
		return vrsta;
	}
	
	public void setVrsta(VrstaKnjige vrsta) {
		this.vrsta = vrsta;
	}
	
	public Izdavac getIzdavac(){
		return this.izdavac;
	}
	
	public void setIzdavac(Izdavac izdavac) {
		this.izdavac = izdavac;
	}
	
	public String getNaslov() {
		return naslov;
	}
	
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	
	public String getOriginalniNaslov() {
		return originalniNaslov;
	}
	
	public void setOriginalniNaslov(String originalniNaslov) {
		this.originalniNaslov = originalniNaslov;
	}
	
	public int getBrojStranica() {
		return brojStranica;
	}
	
	public void setBrojStranica(int brojStranica) {
		this.brojStranica = brojStranica;
	}
	
	public int getGodinaIzdavanja() {
		return godinaIzdavanja;
	}
	
	public void setGodinaIzdavanja(int godinaIzdavanja) {
		this.godinaIzdavanja = godinaIzdavanja;
	}
	
	public int getNegBodovi() {
		return negBodovi;
	}
	
	public void setNegBodovi(int negBodovi) {
		this.negBodovi = negBodovi;
	}
	
	public int getBrojPrimjeraka(){
		return brojPrimjeraka;
	}
	
	public void setBrojPrimjeraka(int brojPrimjeraka){
		this.brojPrimjeraka = brojPrimjeraka;
	}
	
	public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Knjiga)) {
            return false;
        }
        Knjiga other = (Knjiga) obj;
        return this.id == other.id;
    }
}
