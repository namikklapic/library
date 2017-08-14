package bussines;

import jpa.AutorKnjiga;
import jpa.EntityManagerProducer;

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

}
