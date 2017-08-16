package bussines;

import java.util.List;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
import jpa.Nastavnik;
import jpa.NastavnikPredmet;
import jpa.Predmet;

public class NastavnikPredmetServiceBean extends EntityManagerProducer<NastavnikPredmet>{
	
	public List<Predmet> getPredmetiByNastavnik(Integer sifraNastavnika ) {
		List<Predmet> result = null;
		
		try {
			result = em.createQuery("Select np from NastavnikPredmet where np.sifraNastavnik =:sifraNastavnika")
					.setParameter("sifraNastavnika", sifraNastavnika)
					.getResultList();
		} catch(NoResultException nre) {}
		
		return result;
	}
	
	public List<Nastavnik> getNastavniciByPredmet(Integer sifraPredmeta){
		List<Nastavnik> result = null;
		
		try {
			result = em.createQuery("Select np from NastavnikPredmet where np.sifraPredmeta =:sifraPredmeta")
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
		return find;			
	}
}
