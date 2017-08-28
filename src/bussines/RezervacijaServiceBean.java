package bussines;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
import jpa.Knjiga;
import jpa.Korisnik;
import jpa.Posudba;
import jpa.Primjerak;
import jpa.Rezervacija;
import jpa.RezervacijaPK;
import swing.PanelPrijava;
import util.MyEvent;

class RezervacijaComparator implements Comparator<Rezervacija> {
    @Override
    public int compare(Rezervacija o1, Rezervacija o2) {
        return o1.getPrimjerak().getKnjiga().getNaslov().compareToIgnoreCase(o2.getPrimjerak().getKnjiga().getNaslov());
    }
}

public class RezervacijaServiceBean extends EntityManagerProducer<Rezervacija> {
	
	public List<Rezervacija> getActiveRezervacijeByKorisnik(Korisnik k){
		List<Rezervacija> result = null;
		try {
			result = em
					.createQuery("Select r from Rezervacija r where r.korisnik = :k and r.isConfirmed=0")
					.setParameter("k", k)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new RezervacijaComparator());
		} catch(NoResultException nre) {}

		return result;	
	}
	
	public List<Rezervacija> getRezervacijeByKorisnik(Korisnik k){
		List<Rezervacija> result = null;
		try {
			result = em
					.createQuery("Select r from Rezervacija r where r.korisnik = :k")
					.setParameter("k", k)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new RezervacijaComparator());
		} catch(NoResultException nre) {}

		return result;	
	}
	
	public List<Rezervacija> getAllRezervacije(){
		List<Rezervacija> result = null;
		try {
			result = em
					.createQuery("Select r from Rezervacija r")
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new RezervacijaComparator());
		} catch(NoResultException nre) {}

		return result;	
	}
	
	public List<Rezervacija> getAllActiveRezervacije(){
		List<Rezervacija> result = null;
		try {
			result = em
					.createQuery("Select r from Rezervacija r where r.isConfirmed=0")
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new RezervacijaComparator());
		} catch(NoResultException nre) {}

		return result;	
	}
	
	public Integer getRezervacijeCountByKorisnik(Korisnik k){
		Long c = (long) 0;
		try {
			c = (Long) em
					.createQuery("Select count(r) from Rezervacija r where r.korisnik = :k and r.isConfirmed=0")
					.setParameter("k", k)
					.getSingleResult();
		} catch(NoResultException nre) {}
		return c.intValue();
	}
	
	public Rezervacija save(Rezervacija entity) {
		// Once added, rezervacija can not be changed. That is the reason we are not trying to find existing Rezervacija
		super.save(entity);
		MyEvent evt = new MyEvent(this, "Update Rezervacija");
		PanelPrijava.realTime.fireMyEvent(evt);
		return entity;
	}
	
	public List<Rezervacija> getRezervacijeByKnjiga(Knjiga k){
		List<Rezervacija> result = null;
		try {
			result = em
					.createQuery("Select r from Rezervacija r inner join r.primjerak pr where pr.knjiga = :k and r.isConfirmed=0")
					.setParameter('k', 	k)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new RezervacijaComparator());
		} catch(NoResultException nre) {}

		return result;
	}
	
	public boolean isKnjigaRezervisana(Knjiga k){
		
		if(getRezervacijeByKnjiga(k) != null)
			return true;
		
		return false;
	}

	public void setRezervacijaConfirmed(RezervacijaPK id) {
		Rezervacija find = em.find(Rezervacija.class, id);
		if(find != null){
			em.getTransaction().begin();
			find.setIsConfirmed(true);
			em.getTransaction().commit();
			MyEvent evt = new MyEvent(this, "Update Rezervacija");
			PanelPrijava.realTime.fireMyEvent(evt);
		}
	}

	public boolean doesReservationExist(RezervacijaPK id) {
		Rezervacija find = em.find(Rezervacija.class, id);
		if(find != null) {
			if(find.getIsConfirmed() == false)
				return true;
			else 
				return false;
		}
		else
			return false;
	}
}
