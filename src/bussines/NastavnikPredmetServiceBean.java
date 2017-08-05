package bussines;

import jpa.NastavnikPredmet;
import jpa.EntityManagerProducer;

public class NastavnikPredmetServiceBean extends EntityManagerProducer<NastavnikPredmet>{
	
	public NastavnikPredmet save(NastavnikPredmet entity){
		NastavnikPredmet find = em.find(NastavnikPredmet.class, entity.getId());
		if( find != null){
			find.setNastavnik(entity.getNastavnik());
			find.setPredmet(entity.getPredmet());
		}else{
			super.save(entity);
		}
		return find;			
	}
}
