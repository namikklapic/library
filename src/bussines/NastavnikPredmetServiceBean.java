package bussines;

import java.util.List;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
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
		} catch(NoResultException nre) {}
		
		return result;
	}
	
	public List<Nastavnik> getNastavniciByPredmet(Integer sifraPredmeta){
		List<Nastavnik> result = null;
		
		try {
			result = em.createQuery("Select np from NastavnikPredmet np where np.sifraPredmeta =:sifraPredmeta")
					.setParameter("sifraPredmeta", sifraPredmeta)
					.getResultList();
		} catch(NoResultException nre) {}
		
		return result;
	}
	
	public NastavnikPredmet save(NastavnikPredmet entity){
		NastavnikPredmet find = em.find(NastavnikPredmet.class, entity.getId());
		if( find != null){
			em.getTransaction().begin();
			find.setNastavnik(entity.getNastavnik());
			find.setPredmet(entity.getPredmet());
			em.getTransaction().commit();
		}else{
			super.save(entity);
		}
		MyEvent evt = new MyEvent(this, "Update NastavnikPredmet");
		PanelPrijava.realTime.fireMyEvent(evt);
		return find;			
	}
}
