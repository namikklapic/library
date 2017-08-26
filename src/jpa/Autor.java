package jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="autor")
public class Autor implements Serializable, Cloneable, Comparable {

	/* Persistence klasa za kreiranje tabele autor
	 * Klasa ima tri atributa
	 * 		id, koji se generise
	 * 		imeAutora, 
	 * 		prezimeAutora
	 * 
	 * Za konstruktor ove klase vaze ista pravila kao i za VrstuKnjige (pogledati fajl VrstaKnjige.java)
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String imeAutora;
	private String prezimeAutora;
	
	/*
	 * 	Default konstruktor je potreban za JPA.
	 * 	Prilikom persistanja na bazu, Derby driver kreira objekte klase kroz tzv. refleksiju.
	 * 	Refleksija podrazumijeva da se kreira objekat sa konstruktorom koji ne prima argumente, a zatim se 
	 * 	koriste seteri da se vrijednosti parametara promijene.
	 * 
	 */
	public Autor(){}
	
	public Autor(int id, String ime, String prezime){
		this.id = id;
		this.imeAutora = ime;
		this.prezimeAutora = prezime;
	}
	
	public Autor (Autor a) {
		this.id = a.getId();
		this.imeAutora = new String(a.getImeAutora());
		this.prezimeAutora = new String(a.getPrezimeAutora());
	}
	
	public int getId(){
		return id;
	}
	
	public String getImeAutora() {
		return imeAutora;
	}
	
	public void setImeAutora(String imeAutora) {
		this.imeAutora = imeAutora;
	}
	
	public String getPrezimeAutora() {
		return prezimeAutora;
	}
	
	public void setPrezimeAutora(String prezimeAutora) {
		this.prezimeAutora = prezimeAutora;
	}
	
	public String toString() {
		return imeAutora + " " + prezimeAutora;
	}
	
	public Object clone() {
		Autor a = new Autor(this.id, new String(this.imeAutora), new String (this.prezimeAutora));
		return a;
	}
	public boolean equals(Object other) {
		Autor a = (Autor) other;
		return this.id == a.getId();
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
