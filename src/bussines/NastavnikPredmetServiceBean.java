package bussines;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
import jpa.Literatura;
import jpa.Nastavnik;
import jpa.NastavnikPredmet;
import jpa.Predmet;
import swing.PanelPrijava;
import util.MyEvent;



public class NastavnikPredmetServiceBean extends EntityManagerProducer<NastavnikPredmet>{
	
	public List<Predmet> getPredmetiByNastavnik(Nastavnik n ) {
		List<Predmet> result = null;
		
		try {
			result = em.createQuery("Select np.predmet from NastavnikPredmet np where np.nastavnik =:n")
					.setParameter("n", n)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new PredmetComparator());
		} catch(NoResultException nre) {}
		return result;
	}
	
	public List<Nastavnik> getNastavniciByPredmet(Integer sifraPredmeta){
		List<Nastavnik> result = null;
		
		try {
			result = em.createQuery("Select np from NastavnikPredmet np where np.sifraPredmeta =:sifraPredmeta")
					.setParameter("sifraPredmeta", sifraPredmeta)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new NastavnikComparator());
		} catch(NoResultException nre) {}

		return result;
	}
	
	public void deletePredmetiForNastavnik(Nastavnik n) {
		em.getTransaction().begin();
		em.createQuery("Delete from NastavnikPredmet np where np.nastavnik =:n").setParameter("n", n).executeUpdate();
		em.getTransaction().commit();
	}
	
	public NastavnikPredmet save(NastavnikPredmet entity){

		super.save(entity);
		MyEvent evt = new MyEvent(this, "Update NastavnikPredmet");
		PanelPrijava.realTime.fireMyEvent(evt);
		return entity;			
	}
}
