package bussines;

import java.util.List;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
import jpa.Predmet;

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
							  .createQuery("Select p from Predmet where p.sifraPredmeta =:sifraPredmeta")
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
		return entity;
	}
}
