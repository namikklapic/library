package jpa;
import java.io.Serializable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="rezervacija")
public class Rezervacija implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private RezervacijaPK id;
	
	@ManyToOne
	@JoinColumn(name="inventarskiBroj")
	private Primjerak primjerak;
	
	@ManyToOne
	@JoinColumn(name="sifraKorisnika")
	private Korisnik korisnik;
	
	@Temporal(TemporalType.DATE)
	private Date datumRezervacije;
	
	public Rezervacija() {}
	
	public Rezervacija(RezervacijaPK id, Primjerak p, Korisnik k, Date datumRezervacije) {
		this.id = id;
		this.setPrimjerak(p);
		this.setKorisnik(k);
		this.setDatumRezervacije(datumRezervacije);
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

	public Date getDatumRezervacije() {
		return datumRezervacije;
	}

	public void setDatumRezervacije(Date datumRezervacije) {
		this.datumRezervacije = datumRezervacije;
	}
}
