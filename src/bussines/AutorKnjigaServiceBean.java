package bussines;

import javax.persistence.NoResultException;

import jpa.Autor;
import jpa.AutorKnjiga;
import jpa.EntityManagerProducer;
import jpa.Knjiga;

public class AutorKnjigaServiceBean extends EntityManagerProducer<AutorKnjiga> {
	
	public AutorKnjiga save(AutorKnjiga entity) {
		AutorKnjiga find = em.find(AutorKnjiga.class, entity.getId());
		
		if(find != null) {
			em.getTransaction().begin();
			find.setAutor(entity.getAutor());
			find.setKnjiga(entity.getKnjiga());
			find.setRedniBrojAutoraNaKnjizi(entity.getRedniBrojAutoraNaKnjizi());
			em.getTransaction().commit();
		} else {
			super.save(entity);
		}
		
		return find;
	}
	
	public Autor getAutorNaKnjizi(Knjiga k, int rbAutora){
		AutorKnjiga result = null;
		try{
			result = (AutorKnjiga)em
					.createQuery("Select ak from AutorKnjiga ak where ak.knjiga=:k and ak.redniBrojAutoraNaKnjizi=:rbAutora")
					.setParameter("k", k)
					.setParameter("rbAutora", rbAutora)
					.getSingleResult();
		}catch(NoResultException nre) {}
		
		return result.getAutor();
	}
}
