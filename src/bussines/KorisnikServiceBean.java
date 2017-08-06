package bussines;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
import jpa.Korisnik;

public class KorisnikServiceBean extends EntityManagerProducer {
	
	public Integer getCount() {
		Long c = (long) 0;
		try {
			c = (Long)em.createQuery("select count(k) from Korisnik k").getSingleResult();
		} catch(NoResultException nre) {}
		return (Integer) c.intValue();
	}
}
