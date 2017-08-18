package bussines;

import java.util.List;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
import jpa.Knjiga;
import jpa.Korisnik;
import jpa.Rezervacija;

public class RezervacijaServiceBean extends EntityManagerProducer<Rezervacija> {
	
	public List<Rezervacija> getRezervacijeByKorisnik(Korisnik k){
		List<Rezervacija> result = null;
		try {
			result = em
					.createQuery("Select r from Rezervacija r where r.korisnik = :k")
					.setParameter("k", k)
					.getResultList();
		} catch(NoResultException nre) {}
		return result;	
	}
	
	public Integer getRezervacijeCountByKorisnik(Korisnik k){
		Long c = (long) 0;
		try {
			c = (Long) em
					.createQuery("Select count(r) from Rezervacija r where r.korisnik = :k")
					.setParameter("k", k)
					.getSingleResult();
		} catch(NoResultException nre) {}
		return c.intValue();
	}
	
	public Rezervacija save(Rezervacija entity) {
		// Once added, rezervacija can not be changed. That is the reason we are not trying to find existing Rezervacija
		super.save(entity);
		return entity;
	}
}
