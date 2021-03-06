package jpa;

import java.io.Serializable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="posudba")
public class Posudba implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PosudbaPK id;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name="inventarskiBroj")
	private Primjerak primjerak;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name="sifraKorisnika")
	private Korisnik korisnik;
	
	@Temporal(TemporalType.DATE)
	private Date krajnjiDatumVracanja;
	@Temporal(TemporalType.DATE)
	private Date datumVracanja;
	
	
	public Posudba() {}
	
	public Posudba(PosudbaPK id, Primjerak p, Korisnik k, Date krajnjiDatumVracanja) {
		this.id = id;
		this.primjerak = p;
		this.korisnik = k;
		this.krajnjiDatumVracanja = krajnjiDatumVracanja;
		this.datumVracanja = null;
	}
	
	public PosudbaPK getId() {
		return id;
	}
	
	public void setId(PosudbaPK id) {
		this.id = id;
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
