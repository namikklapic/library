package jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LiteraturaPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(insertable=false, updatable=false)
	private String sifraPredmeta;
	@Column(insertable=false, updatable=false)
	private int knjigaId;
	
	
	public LiteraturaPK() {}
	
	public String getSifraPredmeta() {
		return sifraPredmeta;
	}
	
	public void setSifraPredmeta(String sifraPredmeta) {
		this.sifraPredmeta = sifraPredmeta;
	}
	
	public int getKnjigaId() {
		return knjigaId;
	}
	
	public void setKnjigaId(int knjigaId) {
		this.knjigaId = knjigaId;
	}
	
	public boolean equals (Object other){
		if(this == other) return true;
		if(!(other instanceof LiteraturaPK)) return false;
		
		LiteraturaPK castOther = (LiteraturaPK) other;
		return(this.sifraPredmeta.equals(castOther.sifraPredmeta))
				&& 
				(this.knjigaId == castOther.knjigaId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.knjigaId;
		hash = hash * prime + this.sifraPredmeta.hashCode();
	

		return hash;
	}
}
