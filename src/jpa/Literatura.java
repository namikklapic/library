package jpa;

import java.io.Serializable;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="literatura")
public class Literatura implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private LiteraturaPK id;
	
	@ManyToOne
	@JoinColumn(name="knjigaid")
	private Knjiga knjiga;
	
	@ManyToOne
	@JoinColumn(name="sifraPredmeta")
	private Predmet predmet;
	
	private int brojVaznosti;
	private boolean obavezna;
	

	public Literatura() {}
	
	public LiteraturaPK getId() {
		return id;
	}

	public void setId(LiteraturaPK id) {
		this.id = id;
	}

	public Knjiga getKnjiga() {
		return knjiga;
	}

	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}

	public Predmet getPredmet() {
		return predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}
	
	public int getBrojVaznosti() {
		return brojVaznosti;
	}
	
	public void setBrojVaznosti(int brojVaznosti) {
		this.brojVaznosti = brojVaznosti;
	}
	
	public boolean isObavezna() {
		return obavezna;
	}
	
	public void setObavezna(boolean obavezna) {
		this.obavezna = obavezna;
	}
}
