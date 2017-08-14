package bussines;

import jpa.NastavnikPredmet;
import jpa.EntityManagerProducer;

public class NastavnikPredmetServiceBean extends EntityManagerProducer<NastavnikPredmet>{
	
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
