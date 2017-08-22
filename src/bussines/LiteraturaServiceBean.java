package bussines;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
import jpa.Literatura;
import jpa.Predmet;
import jpa.Knjiga;

public class LiteraturaServiceBean extends EntityManagerProducer<Literatura>{
	
	public List<Knjiga> getLiteraturaByPredmet(String predmet){
		
		List<Literatura> lista = null;
		List<Knjiga> result = new ArrayList<Knjiga>();
		
		try{
			lista = em.createQuery("Select l from Literatura l where l.predmet.nazivPredmeta=:predmet")
					.setParameter("predmet", predmet)
					.getResultList();
		}catch(NoResultException nre) {}
		
		if(lista != null){
			for(Literatura l : lista)
				result.add(l.getKnjiga());
		}
		
		return result;
	}
	
	
}
