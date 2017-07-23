package bussines;

import java.util.List;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
import jpa.VrstaKnjige;

public class VrstaKnjigeServiceBean extends EntityManagerProducer<VrstaKnjige> {
	/**
	 * Searches database for VrstaKnjige with given id
	 * @param id VrstaKnjige id
	 * @return VrstaKnjige entity from database
	 */
	public VrstaKnjige getById(int id) {
		VrstaKnjige result = null;
		try {
			result = (VrstaKnjige) em
					.createQuery("select vk from VrstaKnjige vk where vk.id = :id")
					.setParameter("id", id)
					.getSingleResult();
		} catch(NoResultException nre) {
			
		}
		return result;
	}
	/**
	 * Searches database for VrstaKnjige with given name
	 * @param name VrstaKnjige name
	 * @return VrstaKnjige entity from database
	 */
	public VrstaKnjige getByName(String name) {
		VrstaKnjige result = null;
		try {
			result = (VrstaKnjige) em
					.createQuery("select vk from VrstaKnjige vk where vk.nazivVrste = :name")
					.setParameter("name", name)
					.getSingleResult();
		} catch(NoResultException nre) {
			
		}
		return result;
	}
	
	/**
	 * 
	 * @return List of all VrstaKnjige from database
	 */
	public List<VrstaKnjige> getAllVrstaKnjige(){
		List<VrstaKnjige> result = null;
		try {
			result = em.createQuery("select vk from VrstaKnjige vk").getResultList();
		} catch(NoResultException nre) {}
		return result;
	}
	
	/**
	 * 
	 * @return number of VrstaKnjiga in database
	 */
	public Integer getCount() {
		Long c = (long) 0;
		try {
			c = (Long)em.createQuery("Select count(vk) from VrstaKnjige vk").getSingleResult();
		} catch(NoResultException  nre) {}
		
		return (Integer) c.intValue();
	}
	
	
	/**
	 * Save VrstaKnjige to database or change existing one
	 */
	public VrstaKnjige save(VrstaKnjige entity) {
		VrstaKnjige find = em.find(VrstaKnjige.class, entity.getId());
		if(find != null) {
			em.getTransaction().begin();
			find.setNazivVrste(entity.getNazivVrste());
		}
		else {
			super.save(entity);
		}
		return entity;
	}

}
