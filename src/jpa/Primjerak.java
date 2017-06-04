package jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="primjerak")
public class Primjerak implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	private String inventarskiBroj;
	@Temporal(TemporalType.DATE)
	private Date datumnabavke;
	private String stanje;
	
	@ManyToOne
	private Knjiga knjiga;
	
	public Primjerak(){
		
	}
	public Primjerak(String invBroj, Date datumNabavke, String stanje, Knjiga knjiga){
		this.inventarskiBroj = invBroj;
		this.datumnabavke = datumNabavke;
		this.stanje = stanje;
		this.knjiga = knjiga;
	}

	public String getInventarskiBroj() {
		return inventarskiBroj;
	}

	public void setInventarskiBroj(String invertarskiBroj) {
		this.inventarskiBroj = invertarskiBroj;
	}

	public Date getDatumnabavke() {
		return datumnabavke;
	}

	public void setDatumnabavke(Date datumnabavke) {
		this.datumnabavke = datumnabavke;
	}

	public String getStanje() {
		return stanje;
	}

	public void setStanje(String stanje) {
		this.stanje = stanje;
	}

	public Knjiga getKnjiga() {
		return knjiga;
	}

	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}
	
	
	
	
}
