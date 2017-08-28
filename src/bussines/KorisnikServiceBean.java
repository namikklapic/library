package bussines;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.NoResultException;

import jpa.Bibliotekar;
import jpa.EntityManagerProducer;
import jpa.Izdavac;
import jpa.Korisnik;
import jpa.Student;
import swing.PanelPrijava;
import util.MyEvent;

class KorisnikComparator implements Comparator<Korisnik> {
    @Override
    public int compare(Korisnik o1, Korisnik o2) {
        return o1.getImeKorisnika().compareToIgnoreCase(o2.getImeKorisnika());
    }
}

public class KorisnikServiceBean extends EntityManagerProducer {
	
	public Integer getCount() {
		Long c = (long) 0;
		try {
			c = (Long)em.createQuery("select count(k) from Korisnik k").getSingleResult();
		} catch(NoResultException nre) {}
		return (Integer) c.intValue();
	}
	
	public Korisnik save(Korisnik entity) {
		Korisnik find = em.find(Korisnik.class, entity.getSifraKorisnika());
		
		if (find != null) {
			em.getTransaction().begin();
			find.setBrojNegativnihBodova(entity.getBrojNegativnihBodova());
			find.setImeKorisnika(entity.getImeKorisnika());
			find.setPrezimeKorisnika(entity.getPrezimeKorisnika());
			find.setSifraKorisnika(entity.getSifraKorisnika());
			em.getTransaction().commit();
		} else {
			super.save(entity);
		}
		MyEvent evt = new MyEvent(this, "Update Korisnik");
		PanelPrijava.realTime.fireMyEvent(evt);
		return entity;
	}
	
	public List<Korisnik> getAllKorisnik(){
		List<Korisnik> result = null;
		try {
			result = em.createQuery("Select k from Korisnik k").getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new KorisnikComparator());
		} catch(NoResultException nre) {}

		return result;
	}
}
