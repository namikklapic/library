package jpa;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class PosudbaPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(insertable=false, updatable=false)
	private String inventarskiBroj;
	@Column(insertable=false, updatable=false)
	private String sifraKorisnika;
	
	public PosudbaPK(){
		
	}
	
	public String  getInventarskiBroj() {
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
	
	public boolean equals (Object other){
		if(this == other) return true;
		if(!(other instanceof PosudbaPK)) return false;
		
		PosudbaPK castOther = (PosudbaPK) other;
		return(this.sifraKorisnika.equals(castOther.sifraKorisnika))
				&& 
				(this.inventarskiBroj.equals(castOther.inventarskiBroj));
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.inventarskiBroj.hashCode();
		hash = hash * prime + this.sifraKorisnika.hashCode();
	

		return hash;
	}
	
	
	

}
