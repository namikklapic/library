package jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Persistence klasa za kreiranje tabele vrstaKnjiga
 * Klasa ima dva atributa:
 * 		id - koji je primarni kljuc i sam se generira;
 * 		nazivVrste - String koji predstavlja ime za vrstu knjige (diplomski rad, knjiga, radna kniga ..)
 * 
 * Konstruktor prima samo jedan parametar (nazivVrste). U slucaju da se napravi konstruktor koji setuje i id
 * prilikom persistanja na bazu desit ce se exception zbog narusavanja integriteta ( Exception: Cannot persist detached object)
 * Dakle, id se sam generise, tako da se pri konstrukciji objekta, id polje ostavlja praznim.
 */

@Entity
@Table(name="vrstaknjige")
public class VrstaKnjige implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nazivVrste;
	
	/*
	 * 	Default konstruktor je potreban za JPA.
	 * 	Prilikom persistanja na bazu, Derby driver kreira objekte klase kroz tkz. reflekciju.
	 * 	Refleksija podrazumija da se kreira objekat sa konstruktorom koji ne prima argumente, a zatim se 
	 * 	koriste seteri da se vrijednosti paramtera promijene.
	 * 
	 */
	public VrstaKnjige(){
		
	}
	
	public VrstaKnjige(String naziv){
		this.nazivVrste = naziv;
	}
	
	public int getId(){
		return id;
	}
	public String getNazivVrste() {
		return nazivVrste;
	}
	public void setNazivVrste(String nazivVrste) {
		this.nazivVrste = nazivVrste;
	}
	
	
}
