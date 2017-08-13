package jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@Column(insertable=true, updatable=true)
	@Temporal(TemporalType.DATE)
	private Date datumPosudbe;
	
	
	
	public PosudbaPK(){
		
	}
	
	public PosudbaPK(String invBroj, String sifra, Date datumPosudbe) {
		this.inventarskiBroj = invBroj;
		this.sifraKorisnika = sifra;
		this.datumPosudbe = datumPosudbe;
	}
	
	public Date getDatumPosudbe() {
		return datumPosudbe;
	}


	public void setDatumPosudbe(Date datumPosudbe) {
		this.datumPosudbe = datumPosudbe;
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
