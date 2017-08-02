package bussines;

import java.util.List;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
import jpa.Primjerak;

public class PrimjerakServiceBean extends EntityManagerProducer<Primjerak> {
	
	
	public Primjerak getByInvBroj(String invBroj) {
		Primjerak result = null;
		try {
			result = (Primjerak)em
					.createQuery("Select p from Primjerak p where p.inventarskiBroj =: invBroj")
					.setParameter("invBroj", invBroj)
					.getSingleResult();
		} catch(NoResultException nre) {}
		return result;
	}
	public List<Primjerak> getAllPrimjerak(){
		List<Primjerak> result = null;
		try {
			result = em.createQuery("select p from Primjerak p").getResultList();
		} catch(NoResultException nre) {}
		return result;
	}
	public Integer getCount() {
		Long c = (long) 0;
		try {
			c = (Long)em.createQuery("select count(p) from Primjerak p").getSingleResult();
		} catch(NoResultException nre) {}
		return (Integer) c.intValue();
	}
	
	public Primjerak save(Primjerak entity) {
		Primjerak find = em.find(Primjerak.class, entity.getInventarskiBroj());
		if(find != null) {
			em.getTransaction().begin();
			find.setDatumnabavke(entity.getDatumnabavke());
			find.setStanje(entity.getStanje());
			
			//TODO: Consider moving brojPrimjeraka attribute to another table.
			find.setBrojPrimjeraka(entity.getBrojPrimjeraka());
			find.setKnjiga(entity.getKnjiga());
			em.getTransaction().commit();
		} else {
			super.save(entity);
		}
		return entity;
	}
}
