package jpa;

import javax.persistence.*;

@Entity
@Table(name="nastavnikpredmet")
public class NastavnikPredmet {

	@EmbeddedId
	private NastavnikPredmetPK id;
	
	@ManyToOne
	@JoinColumn(name="sifraNastavnika")
	private Nastavnik nastavnik;
	
	@ManyToOne
	@JoinColumn(name="sifraPredmeta")
	private Predmet predmet;
	
	public NastavnikPredmet(){
		
	}

	public Nastavnik getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(Nastavnik nastavnik) {
		this.nastavnik = nastavnik;
	}

	public Predmet getPredmet() {
		return predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}
	
	
	
	
	
}
