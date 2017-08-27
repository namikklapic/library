package jpa;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="autorknjiga")
public class AutorKnjiga implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private AutorKnjigaPK id; 
	
	@Column(name="redniBrojAutoraNaKnjizi")
	private int redniBrojAutoraNaKnjizi;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name="knjigaid")
	private Knjiga knjiga;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name="autorid")
	private Autor autor;
	
	
	public AutorKnjiga() {}
	
	public AutorKnjiga(AutorKnjigaPK id, Knjiga k, Autor a, int rb){
		this.id = id;
		this.knjiga = k;
		this.autor = a;
		this.redniBrojAutoraNaKnjizi = rb;
	}
	
	public AutorKnjigaPK getId() {
		return id;
	}
	
	public void setId(AutorKnjigaPK id) {
		this.id = id;
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

	public int getRedniBrojAutoraNaKnjizi() {
		return redniBrojAutoraNaKnjizi;
	}

	public void setRedniBrojAutoraNaKnjizi(int redniBrojAutoraNaKnjizi) {
		this.redniBrojAutoraNaKnjizi = redniBrojAutoraNaKnjizi;
	}

}
