package bussines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
import jpa.Literatura;
import jpa.Predmet;
import jpa.Knjiga;
import jpa.Korisnik;

class LiteraturaComparator implements Comparator<Literatura> {
    @Override
    public int compare(Literatura o1, Literatura o2) {
        return o1.getKnjiga().getNaslov().compareToIgnoreCase(o2.getKnjiga().getNaslov());
    }
}

public class LiteraturaServiceBean extends EntityManagerProducer<Literatura>{
	
	public List<Knjiga> getLiteraturaByPredmet(String predmet){
		predmet = "%" + predmet + "%";
		List<Literatura> lista = null;
		List<Knjiga> result = new ArrayList<Knjiga>();
		
		try{
			lista = em.createQuery("Select l from Literatura l where l.predmet.nazivPredmeta LIKE :predmet")
					.setParameter("predmet", predmet)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
		}catch(NoResultException nre) {}
		
		if(lista != null){
			for(Literatura l : lista)
				result.add(l.getKnjiga());
			Collections.sort(result, new KnjigaComparator());
		}
		return result;
	}
	public List<Literatura> getLiteraturaOnPredmet (Predmet p){
		List<Literatura> lista = null;
		try {
			lista = em.createQuery("Select l from Literatura l where l.predmet = :predmet")
					.setParameter("predmet", p)
					.getResultList();
			for(int i=0; i<lista.size(); i++)
				em.refresh(lista.get(i));
			Collections.sort(lista, new LiteraturaComparator());
		} catch(NoResultException nre) {}
		return lista;
	}
	public Literatura delete (Literatura l) {
		em.getTransaction().begin();
		em.createQuery("Delete from Literatura l where l = :literatura")
		.setParameter("literatura", l).executeUpdate();
		em.getTransaction().commit();
		return l;
	}
	
	public Literatura save(Literatura entity) {
		Literatura find = em.find(Literatura.class, entity.getId());
		if(find != null) {
			em.getTransaction().begin();
			find.setBrojVaznosti(entity.getBrojVaznosti());
			find.setObavezna(entity.isObavezna());
			em.getTransaction().commit();
		} else {
			super.save(entity);
		}
		return entity;
	}
	
}
