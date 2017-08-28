package bussines;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
import jpa.Student;
import jpa.VrstaKnjige;
import swing.PanelPrijava;
import util.MyEvent;

class VrstaKnjigeComparator implements Comparator<VrstaKnjige> {
    @Override
    public int compare(VrstaKnjige o1, VrstaKnjige o2) {
        return o1.getNazivVrste().compareToIgnoreCase(o2.getNazivVrste());
    }
}

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
			em.refresh(result);
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
			em.refresh(result);
		} catch(NoResultException nre) {
			
		}

		return result;
	}
	
	public List<VrstaKnjige> getVrstaKnjigeByNaziv(String naziv){
		List<VrstaKnjige> result = null;
		naziv = "%" + naziv + "%";
		try{
			result = em.createQuery("Select vk from VrstaKnjige vk where vk.nazivVrste LIKE :naziv")
					.setParameter("naziv", naziv)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new VrstaKnjigeComparator());
			
		}catch(NoResultException nre){}

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
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new VrstaKnjigeComparator());
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
			String naziv = entity.getNazivVrste();
			int sifra = entity.getId();
			em.getTransaction().begin();
			find.setNazivVrste(entity.getNazivVrste());
			em.getTransaction().commit();
		}
		else {
			super.save(entity);
		}
		MyEvent evt = new MyEvent(this, "Update VrstaKnjige");
		PanelPrijava.realTime.fireMyEvent(evt);
		return entity;
	}
	
	public int getIndexOfVrstaKnjige(VrstaKnjige vk){
		List<VrstaKnjige> vkLista = getAllVrstaKnjige();
		for(int i = 0; i < vkLista.size(); i++){
			if(vkLista.get(i).toString().equals(vk.toString()))
				return i;
		}
		return -1;
	}
	
	public boolean existsVrstaKnjige(String vkNaziv){
		if(getByName(vkNaziv) != null)
			return true;
		return false;
	}
}
