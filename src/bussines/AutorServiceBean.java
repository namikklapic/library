package bussines;

import java.util.List;

import javax.persistence.NoResultException;

import jpa.Autor;
import jpa.EntityManagerProducer;

public class AutorServiceBean extends EntityManagerProducer<Autor> {
	
	/**
	 * Searches database for Autor with given id
	 * @param id Autor id 
	 * @return Autor entity
	 */
	public Autor getById( int id) {
		Autor result = null;
		try {
			result = (Autor)em
					.createQuery("select a from Autor a where a.id = :id")
					.setParameter("id", id)
					.getSingleResult();
		} catch(NoResultException nre) {}
		return result;
	}
	
	/**
	 * 
	 * @return List of all Autors from database
	 */
	public List<Autor> getAllAutor(){
		List<Autor> result = null;
		try {
			result = em.createQuery("select a from Autor a").getResultList();
		} catch(NoResultException nre) {}
		return result;
	}
	
	public List<Autor> getAutorByFullName(String firstname, String lastname){
		List<Autor> result = null;
		try{
			result = em.createQuery("Select a from Autor a where a.imeAutora=:firstname and a.prezimeAutora=:lastname")
					.setParameter("firstname", firstname)
					.setParameter("lastname", lastname)
					.getResultList();
		}catch(NoResultException nre){}
		
		return result;
	}
	
	/**
	 * 
	 * @return number of autors in database
	 */
	
	public Integer getCount() {
		Long c = (long) 0;
		try {
			c =(Long) em.createQuery("select count(a) from Autor a").getSingleResult();
		} catch(NoResultException nre) {}
		
		return (Integer) c.intValue();
	}
	
	/**
	 * save Autor to database or change existing one
	 */
	public Autor save(Autor entity) {
		Autor find = em.find(Autor.class, entity.getId());
		if(find != null) {
			em.getTransaction().begin();
			find.setImeAutora(entity.getImeAutora());
			find.setPrezimeAutora(entity.getPrezimeAutora());
			em.getTransaction().commit();
		} else {
			super.save(entity);
		}
		return entity;
	}
	/**
	 * Returns the Autor specified by ime and prezime
	 */
	public Autor getByFirstAndLastName(String ime, String prezime){
		Autor result = null;
		try{
			result = (Autor)em
					.createQuery("Select a from Autor a where a.imeAutora=:ime and a.prezimeAutora=:prezime")
					.setParameter("ime", ime)
					.setParameter("prezime", prezime)
					.getSingleResult();
		}catch(NoResultException nre){}
		
		return result;
	}
	
	public boolean existsAutor(String ime, String prezime){
		if(getByFirstAndLastName(ime, prezime) != null)
			return true;
		return false;
	}

}
