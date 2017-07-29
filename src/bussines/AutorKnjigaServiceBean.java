package bussines;

import jpa.AutorKnjiga;
import jpa.EntityManagerProducer;

public class AutorKnjigaServiceBean extends EntityManagerProducer<AutorKnjiga> {
	public AutorKnjiga save(AutorKnjiga entity) {
		AutorKnjiga find = em.find(AutorKnjiga.class, entity.getId());
		if(find != null) {
			find.setAutor(entity.getAutor());
			find.setKnjiga(entity.getKnjiga());
			find.setRedniBrojAutoraNaKnjizi(entity.getRedniBrojAutoraNaKnjizi());
		} else {
			super.save(entity);
		}
		return find;
	}

}
