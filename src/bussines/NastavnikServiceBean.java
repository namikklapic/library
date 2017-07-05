package bussines;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
import jpa.Nastavnik;

public class NastavnikServiceBean extends EntityManagerProducer<Nastavnik>{
	public Nastavnik findByNameAndSurname(String nameAndSurname, String password){
		Nastavnik result = null;		
		try{
			String ime = nameAndSurname.split("[.]")[0];
			String prezime = nameAndSurname.split("[.]")[1];
			result = (Nastavnik)em
					.createQuery("select n from Nastavnik n inner join  n.korisnik k where k.imeKorisnika=:ime and k.prezimeKorisnika=:prezime and n.password=:password")
					.setParameter("ime",ime )
					.setParameter("prezime", prezime)
					.setParameter("password", password)
					.getSingleResult();
		} catch(NoResultException nre){
			
		} catch(ArrayIndexOutOfBoundsException err){
			
		}
		return result;
	}
	
	public Nastavnik save(Nastavnik entity) {
		Nastavnik find = em.find(Nastavnik.class, entity.getKorisnik().getSifraKorisnika());
		if (find != null) {
			em.getTransaction().begin();
			find.setAkademskoZvanje(entity.getAkademskoZvanje());
			find.setKorisnik(entity.getKorisnik());
			find.setPassword(entity.getPassword());
			em.getTransaction().commit();
		} else {
			super.save(entity);
		}
		return entity;
	}

}
