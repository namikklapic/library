package jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NastavnikPredmetPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(insertable=false, updatable=false)
	private String sifraPredmeta;
	@Column(insertable=false, updatable=false)
	private String sifraNastavnika;
	public String getSifraPredmeta() {
		return sifraPredmeta;
	}
	
	
	public void setSifraPredmeta(String sifraPredmeta) {
		this.sifraPredmeta = sifraPredmeta;
	}
	public String getSifraNastavnika() {
		return sifraNastavnika;
	}
	public void setSifraNastavnika(String sifraNastavnika) {
		this.sifraNastavnika = sifraNastavnika;
	}
	
	
	public boolean equals (Object other){
		if(this == other) return true;
		if(!(other instanceof NastavnikPredmetPK)) return false;
		
		NastavnikPredmetPK castOther = (NastavnikPredmetPK) other;
		return(this.sifraNastavnika.equals(castOther.sifraNastavnika)) 
				&& 
				(this.sifraPredmeta.equals(castOther.sifraPredmeta));
	}
	
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.sifraNastavnika.hashCode();
		hash = hash * prime + this.sifraPredmeta.hashCode();

		return hash;
	}
	
}
