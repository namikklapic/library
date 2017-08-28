package bussines;

import java.util.List;
import java.util.Collections;
import java.util.Comparator;

import javax.persistence.NoResultException;
import jpa.EntityManagerProducer;
import jpa.Knjiga;
import jpa.Primjerak;
import jpa.VrstaKnjige;
import swing.PanelPrijava;
import util.MyEvent;

class KnjigaComparator implements Comparator<Knjiga> {
    @Override
    public int compare(Knjiga o1, Knjiga o2) {
        return o1.getNaslov().compareToIgnoreCase(o2.getNaslov());
    }
}

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
			em.refresh(result);
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
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new KnjigaComparator());
		} catch(NoResultException nre) {}
		return result;
	}
	/**
	 * 
	 * @return List of Knjiga with the specified naslov
	 */
	public List<Knjiga> getKnjigaByNaslov(String naslov) {
		naslov = "%" + naslov + "%";
		List<Knjiga> result = null;
		try{
			result = em.createQuery("Select k from Knjiga k where k.naslov LIKE :naslov")
					.setParameter("naslov", naslov)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new KnjigaComparator());
		}catch(NoResultException nre) {}
		return result;
	}
	/**
	 * 
	 * @return List of Knjiga with the specified izdavac
	 */
	public List<Knjiga> getKnjigaByIzdavac(String naziv) {
		naziv = "%" + naziv + "%";
		List<Knjiga> result = null;
		try{
			result = em.createQuery("Select k from Knjiga k where k.izdavac.nazivIzdavaca LIKE :naziv")
					.setParameter("naziv", naziv)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new KnjigaComparator());
		}catch(NoResultException nre) {}
		return result;
	}
	/**
	 * 
	 * @return List of Knjiga with the specified izdavac
	 */
	public List<Knjiga> getKnjigaByVrsta(String vrsta) {
		vrsta = "%" + vrsta + "%";
		List<Knjiga> result = null;
		try{
			result = em.createQuery("Select k from Knjiga k where k.vrsta.nazivVrste  LIKE :vrsta")
					.setParameter("vrsta", vrsta)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new KnjigaComparator());
		}catch(NoResultException nre) {}
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
		MyEvent evt = new MyEvent(this, "Update Knjiga");
		PanelPrijava.realTime.fireMyEvent(evt);
		return entity;
	}
	
	public boolean existsKnjiga(String naslov, String orignaslov, int godina, String vrsta, String izdavac){
		Knjiga result = null;
		try{
			result = (Knjiga)em.createQuery("Select k from Knjiga k where k.naslov=:naslov and k.originalniNaslov=:orignaslov and k.godinaIzdavanja=:godina and k.vrsta.nazivVrste=:vrsta and k.izdavac.nazivIzdavaca=:izdavac")
					.setParameter("naslov", naslov)
					.setParameter("orignaslov", orignaslov)
					.setParameter("godina", godina)
					.setParameter("vrsta", vrsta)
					.setParameter("izdavac", izdavac)
					.getSingleResult();
		}catch(NoResultException nre){}
		
		if(result != null)
			return true;
		return false;
	}
	
}
