package jpa;

import java.io.Serializable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="posudba")
public class Posudba implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PosudbaPK id;
	
	@ManyToOne
	@JoinColumn(name="inventarskiBroj")
	private Primjerak primjerak;
	
	
	@ManyToOne
	@JoinColumn(name="sifraKorisnika")
	private Korisnik korisnik;
	
	@Temporal(TemporalType.DATE)
	private Date datumPosudbe;
	@Temporal(TemporalType.DATE)
	private Date krajnjiDatumVracanja;
	@Temporal(TemporalType.DATE)
	private Date datumVracanja;
	
	
	public Posudba(){
		
	}
	
	public Primjerak getPrimjerak() {
		return primjerak;
	}

	public void setPrimjerak(Primjerak primjerak) {
		this.primjerak = primjerak;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public PosudbaPK getId() {
		return id;
	}
	public void setId(PosudbaPK id) {
		this.id = id;
	}
	public Date getDatumPosudbe() {
		return datumPosudbe;
	}
	public void setDatumPosudbe(Date datumPosudbe) {
		this.datumPosudbe = datumPosudbe;
	}
	public Date getKrajnjiDatumVracanja() {
		return krajnjiDatumVracanja;
	}
	public void setKrajnjiDatumVracanja(Date krajnjiDatumVracanja) {
		this.krajnjiDatumVracanja = krajnjiDatumVracanja;
	}
	public Date getDatumVracanja() {
		return datumVracanja;
	}
	public void setDatumVracanja(Date datumVracanja) {
		this.datumVracanja = datumVracanja;
	}
	
	
	
	

}
