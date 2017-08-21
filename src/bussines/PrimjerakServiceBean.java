package bussines;

import java.util.List;
import jpa.Knjiga;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import jpa.EntityManagerProducer;
import jpa.Primjerak;

public class PrimjerakServiceBean extends EntityManagerProducer<Primjerak> {
	
	
	public Primjerak getByInvBroj(String invBroj) {
		Primjerak result = null;
		try {
			result = (Primjerak)em
					.createQuery("Select p from Primjerak p where p.inventarskiBroj=:invBroj")
					.setParameter("invBroj", invBroj)
					.getSingleResult();
		} catch(NoResultException nre) {}
		return result;
	}
	
	public List<Primjerak> getAllAvailablePrimjerakByBook(Knjiga k) {
		List<Primjerak> result = null;
		try {
			Query query = em.createQuery("select p from Primjerak p where p.knjiga=:k and p.posudjen=0 and p.rezervisan=0");
			query.setParameter("k",k);
			result = query.getResultList();
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
			
			find.setKnjiga(entity.getKnjiga());
			em.getTransaction().commit();
		} else {
			super.save(entity);
		}
		return entity;
	}
	
	public void setPrimjerakPosudjen(Primjerak entity, boolean state){
		Primjerak find = em.find(Primjerak.class, entity.getInventarskiBroj());
		if(find != null){
			em.getTransaction().begin();
			find.setPosudjen(state);
			em.getTransaction().commit();
		}
	}
	
	public void setPrimjerakRezervisan(Primjerak entity, boolean state){
		Primjerak find = em.find(Primjerak.class, entity.getInventarskiBroj());
		if(find != null){
			em.getTransaction().begin();
			find.setRezervisan(state);
			em.getTransaction().commit();
		}
	}
}
