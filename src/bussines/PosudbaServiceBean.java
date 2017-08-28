package bussines;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
import jpa.Knjiga;
import jpa.Korisnik;
import jpa.Posudba;
import jpa.Predmet;
import swing.PanelPrijava;
import util.MyEvent;

class PosudbaComparator implements Comparator<Posudba> {
    @Override
    public int compare(Posudba o1, Posudba o2) {
        return o1.getPrimjerak().getKnjiga().getNaslov().compareToIgnoreCase(o2.getPrimjerak().getKnjiga().getNaslov());
    }
}

public class PosudbaServiceBean extends EntityManagerProducer<Posudba> {
	
	
	public Integer getCount() {
		Long c = (long) 0;
		try {
			c = (Long) em.createQuery("Select count(p) from Posudba p").getSingleResult();
		} catch(NoResultException nre) {}
		return c.intValue();
	}
	
	public List<Posudba> getAllPosudbe(){
		List<Posudba> result = null;
		try {
			result = em
				.createQuery("Select p from Posudba p")
				.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new PosudbaComparator());
			}
		catch(NoResultException nre) {}
		return result;	
	}
	
	public List<Posudba> getPosudbeByKorisnik(Korisnik k){
		List<Posudba> result = null;
		try {
			result = em
					.createQuery("Select p from Posudba p where p.korisnik = :k")
					.setParameter("k", k)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new PosudbaComparator());
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
					.setParameter("k", 	k)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new PosudbaComparator());
		} catch(NoResultException nre) {}
		return result;
	}
	
	public List<Posudba> getActivePosudbaByUserBookFilter(Korisnik k, String filter){
		filter = "%" + filter + "%";
		List<Posudba> result = null;
		try {
			result = em
					.createQuery("Select p from Posudba p inner join p.primjerak pr where p.korisnik = :k and pr.knjiga.naslov LIKE :filter and p.datumVracanja IS NULL")
					.setParameter("k", 	k)
					.setParameter("filter", filter)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new PosudbaComparator());
		} catch(NoResultException nre) {}
		return result;
	}
	
	public List<Posudba> getAllPosudbeByUserBookFilter(Korisnik k, String filter){
		filter = "%" + filter + "%";
		List<Posudba> result = null;
		try {
			result = em
					.createQuery("Select p from Posudba p inner join p.primjerak pr where p.korisnik = :k and pr.knjiga.naslov LIKE :filter")
					.setParameter("k", 	k)
					.setParameter("filter", filter)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new PosudbaComparator());
		} catch(NoResultException nre) {}
		return result;
	}
	
	
	public List<Posudba> getActivePosudbaByUserFilter(String filter){
		filter = "%" + filter + "%";
		List<Posudba> result = null;
		try {
			result = em
					.createQuery("Select p from Posudba p where (p.korisnik.imeKorisnika LIKE :filter or p.korisnik.prezimeKorisnika LIKE :filter) and p.datumVracanja IS NULL")
					.setParameter("filter", filter)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new PosudbaComparator());
		} catch(NoResultException nre) {}
		return result;
	}
	
	public List<Posudba> getAllPosudbeByUserFilter(String filter){
		filter = "%" + filter + "%";
		List<Posudba> result = null;
		try {
			result = em
					.createQuery("Select p from Posudba p where p.korisnik.imeKorisnika LIKE :filter or p.korisnik.prezimeKorisnika LIKE :filter")
					.setParameter("filter", filter)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			System.out.println(result.size());
			Collections.sort(result, new PosudbaComparator());
		} catch(NoResultException nre) {}
		return result;
	}
	
	
	
	public List<Posudba> getActivePosudba(){
		List<Posudba> result = null;
		try {
			result = em
					.createQuery("Select p from Posudba p where p.datumVracanja IS NULL")
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new PosudbaComparator());
		} 
		catch(NoResultException nre) {}

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
					.createQuery("Select p from Posudba p inner join p.primjerak pr where pr.knjiga=:k and p.datumVracanja IS NULL")
					.setParameter("k", k)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new PosudbaComparator());
		} catch(NoResultException nre) {}
		
		return result;
	}
	
	public List<Posudba> getActivePosudbaByBookFilter(String filter){
		filter = "%" + filter + "%";
		List<Posudba> result = null;
		try {
			result = em
					.createQuery("Select p from Posudba p inner join p.primjerak pr where pr.knjiga.naslov LIKE :filter and p.datumVracanja IS NULL")
					.setParameter("filter", filter)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new PosudbaComparator());
		} catch(NoResultException nre) {}
		
		return result;
	}
	
	public List<Posudba> getAllPosudbeByBookFilter(String filter){
		filter = "%" + filter + "%";
		List<Posudba> result = null;
		try {
			result = em
					.createQuery("Select p from Posudba p inner join p.primjerak pr where pr.knjiga.naslov LIKE :filter")
					.setParameter("filter", filter)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new PosudbaComparator());
		} catch(NoResultException nre) {}
		
		return result;
	}
	
	
	
	public Posudba save(Posudba entity) {
		// Once added, posudba can not be changed. That is the reason we are not trying to find existing Posudba
		super.save(entity);
		MyEvent evt = new MyEvent(this, "Update Posudba");
		PanelPrijava.realTime.fireMyEvent(evt);
		return entity;
	}
	
	public void zakljuciPosudbu(Posudba entity){
		Date datum = new Date(); //today's date
		
		Posudba find = em.find(Posudba.class, entity.getId());
		if (find != null) {
			em.getTransaction().begin();
			find.setDatumVracanja(datum);
			em.getTransaction().commit();
			MyEvent evt = new MyEvent(this, "Close Posudba");
			PanelPrijava.realTime.fireMyEvent(evt);
		} 
	}
	
	public List<Posudba> getActivePosudbaByBook(String book){
		List<Posudba> result = null;
		try {
			result = em
					.createQuery("Select p from Posudba p inner join p.primjerak pr where pr.knjiga.naslov=:book and p.datumVracanja IS NULL")
					.setParameter("book", book)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new PosudbaComparator());
		} catch(NoResultException nre) {}
		
		return result;
	}
	
	public List<Posudba> getAllPosudbeByBook(String book){
		List<Posudba> result = null;
		try {
			result = em
					.createQuery("Select p from Posudba p inner join p.primjerak pr where pr.knjiga.naslov=:book")
					.setParameter("book", book)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new PosudbaComparator());
		} catch(NoResultException nre) {}
		
		return result;
	}
	
}
