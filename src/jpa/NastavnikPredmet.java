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
	
	public NastavnikPredmet(NastavnikPredmetPK id, Nastavnik n, Predmet p){
		this.id = id;
		this.nastavnik = n;
		this.predmet = p;
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
	
	public NastavnikPredmetPK getId(){
		return id;
	}
	
	
}
