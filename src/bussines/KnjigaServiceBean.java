package bussines;

import java.util.List;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
import jpa.Knjiga;
import jpa.VrstaKnjige;

public class KnjigaServiceBean extends EntityManagerProducer<Knjiga> {
	/**
	 * Searches database for Knjiga entity with given id.
	 * @param id - Book id
	 * @return Knjiga entity from database
	 */
	public Knjiga getById(int id) {
		Knjiga result = null;
		try {
			result = (Knjiga)em
					.createQuery("Select k from Knjiga k where k.id =:id")
					.setParameter("id", id)
					.getSingleResult();
		} catch(NoResultException nre) {}
		return result;
	}
	/**
	 * 
	 * @return Number of Knjiga entities in database.
	 */
	public Integer getCount() {
		Long c = (long) 0;
		try {
			c = (Long) em.createQuery("Select count(k) from Knjiga k").getSingleResult();
		} catch(NoResultException nre) {}
		return (Integer) c.intValue();
	}
	/**
	 * 
	 * @return List of all Knjiga from database
	 */
	public List<Knjiga> getAllKnjige (){
		List<Knjiga> result = null;
		try {
			result = em.createQuery("Select k from Knjiga k").getResultList();
		} catch(NoResultException nre) {}
		return result;
	}
	/**
	 * Save Knjiga to database or change existing one.
	 */
	public Knjiga save(Knjiga entity) {
		Knjiga find = em.find(Knjiga.class, entity.getId());
		if(find != null) {
			em.getTransaction().begin();
			find.setNaslov(entity.getNaslov());
			find.setOriginalniNaslov(entity.getOriginalniNaslov());
			find.setBrojStranica(entity.getBrojStranica());
			find.setGodinaIzdavanja(entity.getGodinaIzdavanja());
			find.setNegBodovi(entity.getNegBodovi());
			find.setIzdavac(entity.getIzdavac());
			find.setVrsta(entity.getVrsta());
			find.setBrojPrimjeraka(entity.getBrojPrimjeraka());
			em.getTransaction().commit();
		} else {
			super.save(entity);
		}
		return entity;
	}

}
