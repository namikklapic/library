package jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="autor")
public class Autor implements Serializable {

	/* Persistence klasa za kreiranje tabele autor
	 * Klasa ima tri atributa
	 * 		id, koji se generise
	 * 		imeAutora, 
	 * 		prezimeAutora
	 * 
	 * Za konstruktor ove metode klase vaze ista pravila kao i za VrstuKnjige (pogledati fajl VrstaKnjige.java)
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String imeAutora;
	private String prezimeAutora;
	
	/*
	 * 	Default konstruktor je potreban za JPA.
	 * 	Prilikom persistanja na bazu, Derby driver kreira objekte klase kroz tkz. reflekciju.
	 * 	Refleksija podrazumija da se kreira objekat sa konstruktorom koji ne prima argumente, a zatim se 
	 * 	koriste seteri da se vrijednosti paramtera promijene.
	 * 
	 */
	public Autor(){
		
	}
	
	public Autor(String ime, String prezime){
		imeAutora = ime;
		prezimeAutora = prezime;
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
	
	

}
