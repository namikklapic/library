package bussines;

import javax.persistence.NoResultException;

import jpa.Bibliotekar;
import jpa.EntityManagerProducer;

public class BibliotekarServiceBean extends EntityManagerProducer<Bibliotekar>{
	
	public Bibliotekar findByNameAndSurname(String nameAndSurname, String password){
		Bibliotekar result = null;		
		try{
			String ime = nameAndSurname.split("[.]")[0];
			String prezime = nameAndSurname.split("[.]")[1];
			result = (Bibliotekar)em
					.createQuery("select b from Bibliotekar b inner join  b.korisnik k where k.imeKorisnika=:ime and k.prezimeKorisnika=:prezime and b.password=:password")
					.setParameter("ime",ime )
					.setParameter("prezime", prezime)
					.setParameter("password", password)
					.getSingleResult();
		} catch(NoResultException nre){
			
		} catch(ArrayIndexOutOfBoundsException err){
			
		}
		return result;
	}
	
	public Bibliotekar save(Bibliotekar entity) {
		Bibliotekar find = em.find(Bibliotekar.class, entity.getKorisnik().getSifraKorisnika());
		
		if (find != null) {
			em.getTransaction().begin();
			find.setKorisnik(entity.getKorisnik());
			find.setPassword(entity.getPassword());
			em.getTransaction().commit();
		} else {
			super.save(entity);
		}
		
		return entity;
	}
}
