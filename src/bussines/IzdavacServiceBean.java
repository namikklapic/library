package bussines;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.NoResultException;

import jpa.Autor;
import jpa.EntityManagerProducer;
import jpa.Izdavac;
import swing.PanelPrijava;
import util.MyEvent;

class IzdavacComparator implements Comparator<Izdavac> {
    @Override
    public int compare(Izdavac o1, Izdavac o2) {
        return o1.getNazivIzdavaca().compareToIgnoreCase(o2.getNazivIzdavaca());
    }
}

public class IzdavacServiceBean extends EntityManagerProducer<Izdavac> {
	/**
	 * Searches database for Izdavac with given id.
	 * @param id Izdavac id
	 * @return	Izdavac entity from database
	 */
	public Izdavac getById(int id) {
		Izdavac result = null;
		try {
			result = (Izdavac)em
					.createQuery("select i from Izdavac i where i.id = :id")
					.setParameter("id", id)
					.getSingleResult();
			em.refresh(result);
		} catch(NoResultException nre) {
			
		}
		return result;
	}
	
	/**
	 * Searches database for Izdavac with given name.
	 * @param name Izdavac name
	 * @return Izdavac entity from database
	 */
	public Izdavac getByName(String name) {
		Izdavac result = null;
		try {
			result = (Izdavac)em
					.createQuery("select i from Izdavac i where i.nazivIzdavaca=:name")
					.setParameter("name", name)
					.getSingleResult();
			em.refresh(result);
		} catch(NoResultException nre) {
			
		}
		return result;
	}
	
	public List<Izdavac> getIzdavacByNaziv(String naziv){
		List<Izdavac> result = null;
		try{
			result = em.createQuery("Select i from Izdavac i where i.nazivIzdavaca=:naziv")
					.setParameter("naziv", naziv)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new IzdavacComparator());
		}catch(NoResultException nre){}
		return result;
	}
	
	/**
	 * 
	 * @return List of all Izdavac in database
	 */
	public List<Izdavac> getAllIzdavac() {
		List<Izdavac> result = null;
		try {
			result = em.createQuery("select i from Izdavac i").getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new IzdavacComparator());
		} catch(NoResultException nre) {}

		return result;
	}
	
	/**
	 * 
	 * @return number of Izdavac in database
	 */
	public Integer getCount() {
		Long c = (long) 0;
		try {
			c = (Long)em.createQuery("select count(i) from Izdavac i").getSingleResult();
		} catch(NoResultException nre) {}
		return (Integer) c.intValue();
		
	}
	
	/**
	 * Save Izdavac to database or change existing one
	 */
	public Izdavac save(Izdavac entity) {
		Izdavac find = em.find(Izdavac.class, entity.getId());
		if(find != null) {
			em.getTransaction().begin();
			find.setNazivIzdavaca(entity.getNazivIzdavaca());
			em.getTransaction().commit();
		}
		else {
			super.save(entity);
		}
		MyEvent evt = new MyEvent(this, "Update Izdavac");
		PanelPrijava.realTime.fireMyEvent(evt);
		return entity;
	}
	
	public int getIndexOfIzdavac(Izdavac izd){
		List<Izdavac> izdLista = getAllIzdavac();
		for(int i = 0; i < izdLista.size(); i++){
			if(izdLista.get(i).toString().equals(izd.toString()))
				return i;
		}
		return -1;
	}
	
	public boolean existsIzdavac(String izdavac){
		if(getByName(izdavac) != null)
			return true;
		return false;
	}
}
