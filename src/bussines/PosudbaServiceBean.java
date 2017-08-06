package bussines;

import java.util.List;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
import jpa.Knjiga;
import jpa.Korisnik;
import jpa.Posudba;

public class PosudbaServiceBean extends EntityManagerProducer<Posudba> {
	
	
	
	public Integer getCount() {
		Long c = (long) 0;
		try {
			c = (Long) em.createQuery("Select count(p) from Posudba p").getSingleResult();
		} catch(NoResultException nre) {}
		return c.intValue();
	}
	
	public List<Posudba> getPosudbeByKorisnik(Korisnik k){
		List<Posudba> result = null;
		try {
			result = em
					.createQuery("Select p from Posudba p where p.korisnik = :k")
					.setParameter("k", k)
					.getResultList();
		} catch(NoResultException nre) {}
		return result;	
	}
	
	public Integer getActivePosudbaCountByKorisnik(Korisnik k){
		Long c = (long) 0;
		try {
			c = (Long) em
					.createQuery("Select count(p) from Posudba p where p.korisnik = :k and p.datumVracanja IS NULL")
					.setParameter("k", k)
					.getSingleResult();
		} catch(NoResultException nre) {}
		return c.intValue();
	}
	public List<Posudba> getActivePosudbaByKorisnik(Korisnik k){
		List<Posudba> result = null;
		try {
			result = em
					.createQuery("Select p from Posudba p where p.korisnik = :k and p.datumVracanja IS NULL")
					.setParameter('k', 	k)
					.getResultList();
		} catch(NoResultException nre) {}
		return result;
	}
	
	public Integer getPosudbaCountByKnjiga(Knjiga k){
		Long c = (long) 0;
		try {
			c = (Long) em
					.createQuery("Select count(p) from Posudba p inner join p.primjerak pr where pr.knjiga =: k") 
					.setParameter("k", k)
					.getSingleResult();
		}catch(NoResultException nre) {}
		
		return c.intValue();
	}
	
	public List<Posudba> getPosudbaByKnjiga(Knjiga k){
		List<Posudba> result = null;
		try {
			result = em
					.createQuery("Select p from Posudba p inner join p.primjerak pr where pr.knjiga = :k and p.datumVracanja IS NULL")
					.setParameter('k', 	k)
					.getResultList();
		} catch(NoResultException nre) {}
		return result;
	}
	
	public Posudba save(Posudba entity) {
		// Once added, posudba can not be changed. That is the reason we are not trying to find existing Posudba
		super.save(entity);
		return entity;
	}

}
