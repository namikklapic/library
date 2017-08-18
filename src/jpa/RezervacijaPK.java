package jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class RezervacijaPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Column(insertable=false, updatable=false)
	private String inventarskiBroj;
	@Column(insertable=false, updatable=false)
	private String sifraKorisnika;
	
	public RezervacijaPK() {}
	
	public RezervacijaPK(String invBroj, String sifra) {
		this.setInventarskiBroj(invBroj);
		this.setSifraKorisnika(sifra);
	}
	
	public boolean equals (Object other){
		if(this == other) return true;
		if(!(other instanceof RezervacijaPK)) return false;
		
		RezervacijaPK castOther = (RezervacijaPK) other;
		return(this.getSifraKorisnika().equals(castOther.getSifraKorisnika()))
				&& 
				(this.getInventarskiBroj().equals(castOther.getInventarskiBroj()));
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.getInventarskiBroj().hashCode();
		hash = hash * prime + this.getSifraKorisnika().hashCode();
	

		return hash;
	}

	public String getInventarskiBroj() {
		return inventarskiBroj;
	}

	public void setInventarskiBroj(String inventarskiBroj) {
		this.inventarskiBroj = inventarskiBroj;
	}

	public String getSifraKorisnika() {
		return sifraKorisnika;
	}

	public void setSifraKorisnika(String sifraKorisnika) {
		this.sifraKorisnika = sifraKorisnika;
	}
}
