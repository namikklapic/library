package jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="autorknjiga")
public class AutorKnjiga implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private AutorKnjigaPK id; 
	
	@Column(name="redniBrojAutoraNaKnjizi")
	private int redniBrojAutoraNaKnjizi;
	
	@ManyToOne
	@JoinColumn(name="knjigaid")
	private Knjiga knjiga;
	
	@ManyToOne
	@JoinColumn(name="autorid")
	private Autor autor;
	
	public AutorKnjiga(){
		
	}
	

	public Knjiga getKnjiga() {
		return knjiga;
	}

	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public AutorKnjigaPK getId() {
		return id;
	}

	public void setId(AutorKnjigaPK id) {
		this.id = id;
	}

	public int getRedniBrojAutoraNaKnjizi() {
		return redniBrojAutoraNaKnjizi;
	}

	public void setRedniBrojAutoraNaKnjizi(int redniBrojAutoraNaKnjizi) {
		this.redniBrojAutoraNaKnjizi = redniBrojAutoraNaKnjizi;
	}

}
