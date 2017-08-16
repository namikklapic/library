package jpa;

import javax.persistence.*;

@Entity
@Table(name="predmet")
public class Predmet {

	@Id
	private String sifraPredmeta;
	
	private String nazivPredmeta;
	private String skraceniNazivPredmeta;
	private int brojSemestra;
	
	public Predmet() {}
	
	public Predmet(String sifraPredmeta, String naziv, String skraceninaziv, int semestar){
		this.sifraPredmeta = sifraPredmeta;
		this.nazivPredmeta = naziv;
		this.skraceniNazivPredmeta = skraceninaziv;
		this.brojSemestra = semestar;
	}
	
	public String getSifraPredmeta() {
		return sifraPredmeta;
	}
	
	public void setSifraPredmeta(String sifraPredmeta) {
		this.sifraPredmeta = sifraPredmeta;
	}
	
	public String getNazivPredmeta() {
		return nazivPredmeta;
	}
	
	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}
	
	public String getSkraceniNazivPredmeta() {
		return skraceniNazivPredmeta;
	}
	
	public void setSkraceniNazivPredmeta(String skraceniNazivPredmeta) {
		this.skraceniNazivPredmeta = skraceniNazivPredmeta;
	}
	
	public int getBrojSemestra() {
		return brojSemestra;
	}
	
	public void setBrojSemestra(int brojSemestra) {
		this.brojSemestra = brojSemestra;
	}	
	
	public String toString(){
		return nazivPredmeta;
	}
}
