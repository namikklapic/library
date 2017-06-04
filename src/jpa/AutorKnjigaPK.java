package jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AutorKnjigaPK implements Serializable{

	/**
	 * Default serial version id, potreban od strane Serializable interfeja
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(insertable=false, updatable=false)
	private int autorId;
	@Column(insertable=false, updatable=false)
	private int knjigaId;
	
	public AutorKnjigaPK(){
		
	}

	public int getAutorId() {
		return autorId;
	}

	public void setAutorId(int autorId) {
		this.autorId = autorId;
	}

	public int getKnjigaId() {
		return knjigaId;
	}

	public void setKnjigaId(int knjigaId) {
		this.knjigaId = knjigaId;
	}

	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 * S obzirom da ce ova klasa da bude primarni kljuc
	 * potreban joj je method equals, kako bi se kljucevi mogli porediti;
	 */
	
	public boolean equals (Object other){
		if(this == other) return true;
		if(!(other instanceof AutorKnjigaPK)) return false;
		
		AutorKnjigaPK castOther = (AutorKnjigaPK) other;
		return(this.autorId == castOther.autorId) && (this.knjigaId == castOther.knjigaId);
	}
	
	
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.autorId;
		hash = hash * prime + this.knjigaId;
		
		return hash;
	}
	
	

}
