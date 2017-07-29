package jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="izdavac")
public class Izdavac implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String nazivIzdavaca;
	
	/*
	 * 	Default konstruktor je potreban za JPA.
	 * 	Prilikom persistanja na bazu, Derby driver kreira objekte klase kroz tkz. reflekciju.
	 * 	Refleksija podrazumija da se kreira objekat sa konstruktorom koji ne prima argumente, a zatim se 
	 * 	koriste seteri da se vrijednosti paramtera promijene.
	 * 
	 */
	public Izdavac(){
		
	}
	public Izdavac(int id, String naziv){
		this.id = id;
		this.nazivIzdavaca = naziv;
	}
	
	public int getId() {
		return id;
	}
	public String getNazivIzdavaca() {
		return nazivIzdavaca;
	}
	public void setNazivIzdavaca(String nazivIzdavaca) {
		this.nazivIzdavaca = nazivIzdavaca;
	}
	public String toString() {
		return nazivIzdavaca;
	}
	
	
	

}
