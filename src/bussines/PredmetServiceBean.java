package bussines;

import java.util.List;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
import jpa.Predmet;
import swing.PanelPrijava;
import util.MyEvent;

public class PredmetServiceBean extends EntityManagerProducer<Predmet> {
	/**
	 * Searches database for Predmet entity by given id.
	 * @param id - Predmet id
	 * @return Predmet entity from database
	 */
	public Predmet getBySifraPredmeta(String sifraPredmeta){
		Predmet result = null;
		try{
			result = (Predmet)em
							  .createQuery("Select p from Predmet p where p.sifraPredmeta=:sifraPredmeta")
							  .setParameter("sifraPredmeta", sifraPredmeta)
							  .getSingleResult();
		}catch(NoResultException nre) {}
		return result;
	}	
	/**
	 * 
	 * @return Number of Predmet entities in database.
	 */
	public Integer getCount(){
		Long c = (long) 0;
		try{
			c = (Long) em.createQuery("Select count(p) from Predmet p").getSingleResult();
		}catch(NoResultException nre) {}
		return (Integer) c.intValue();
	}	
	/**
	 * 
	 * @return List of all Predmet from database
	 */
	public List<Predmet> getAllPredmeti(){
		List<Predmet> result = null;
		try{
			result = em.createQuery("Select p from Predmet p").getResultList();
		}catch(NoResultException nre) {}
		return result;
	}
	
	public List<Predmet> getAll(){
		return this.getAllPredmeti();
	}
	
	
	
	/**
	 * Save Predmet to database or change existing one.
	 */
	public Predmet save(Predmet entity){
		Predmet find = em.find(Predmet.class, entity.getSifraPredmeta());
		if(find != null){
			em.getTransaction().begin();
			find.setNazivPredmeta(entity.getNazivPredmeta());
			find.setSkraceniNazivPredmeta(entity.getSkraceniNazivPredmeta());
			find.setBrojSemestra(entity.getBrojSemestra());
			em.getTransaction().commit();
		}else{
			super.save(entity);
		}
		MyEvent evt = new MyEvent(this, "Update Predmet");
		PanelPrijava.realTime.fireMyEvent(evt);
		return entity;
	}
	
	public Predmet getByNazivPredmeta(String naziv){
		Predmet result = null;
		try{
			result = (Predmet)em
					.createQuery("Select p from Predmet p where p.nazivPredmeta=:naziv")
					.setParameter("naziv", naziv)
					.getSingleResult();
		}catch(NoResultException nre){}
		
		return result;
	}
	
	public List<Predmet> getPredmetiByNaziv(String naziv){
		List<Predmet> result = null;
		try{
			result = em.createQuery("Select p from Predmet p where p.nazivPredmeta=:naziv")
					.setParameter("naziv", naziv)
					.getResultList();
		}catch(NoResultException nre){}
		
		return result;
	}
	
	public Predmet getBySkraceniNaziv(String skrNaziv){
		Predmet result = null;
		try{
			result = (Predmet)em
					.createQuery("Select p from Predmet p where p.skraceniNazivPredmeta=:skrNaziv")
					.setParameter("skrNaziv", skrNaziv)
					.getSingleResult();
		}catch(NoResultException nre) {}
		
		return result;
	}
	
	public List<Predmet> getPredmetiBySkracenica(String skracenica){
		List<Predmet> result = null;
		try{
			result = em.createQuery("Select p from Predmet p where p.skraceniNazivPredmeta=:skracenica")
					.setParameter("skracenica", skracenica)
					.getResultList();
		}catch(NoResultException nre){}
		
		return result;
	}
	
	public List<Predmet> getPredmetiBySemestar(int sem){
		List<Predmet> result = null;
		try{
			result = em.createQuery("Select p from Predmet p where p.brojSemestra=:sem")
					.setParameter("sem", sem)
					.getResultList();
		}catch(NoResultException nre){}
		
		return result;
	}
	
	public boolean existsPredmetByNaziv(String naziv){
		if(getByNazivPredmeta(naziv) != null)
			return true;
		return false;
	}
	
	public boolean existsPredmetBySkraceniNaziv(String naziv){
		if(getBySkraceniNaziv(naziv) != null)
			return true;
		return false;
	}
	
	public boolean existsPredmetBySifra(String sifra){
		if(getBySifraPredmeta(sifra) != null)
			return true;
		return false;
	}
}
