package bussines;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import jpa.Autor;
import jpa.AutorKnjiga;
import jpa.EntityManagerProducer;
import jpa.Knjiga;
import swing.PanelPrijava;
import util.MyEvent;

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
		MyEvent evt = new MyEvent(this, "Update AuthorKnjiga");
		PanelPrijava.realTime.fireMyEvent(evt);
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
			em.refresh(result);
		}catch(NoResultException nre) {}
		
		return result.getAutor();
	}
	
	public List<Knjiga> getKnjigeByAutor(String ime, String prezime){
		List<AutorKnjiga> lista = null;
		List<Knjiga> result = new ArrayList<Knjiga>();
		
		try{
			lista = em.createQuery("Select ak from AutorKnjiga ak where ak.autor.imeAutora=:ime and ak.autor.prezimeAutora=:prezime")
					.setParameter("ime", ime)
					.setParameter("prezime", prezime)
					.getResultList();
			for(int i=0; i<lista.size(); i++)
				em.refresh(lista.get(i));
		}catch(NoResultException nre) {}
		
		if(lista != null){
			for(AutorKnjiga ak : lista)
				result.add(ak.getKnjiga());
		}
		return result;
	}
	public List<Autor>  getAutorsOnKnjiga(Knjiga k ) {
		List<Autor> result = new ArrayList<Autor>();
		List<AutorKnjiga> akList = new ArrayList<AutorKnjiga>();
		try {
			akList = em.createQuery("select ak from AutorKnjiga ak where ak.knjiga=:k")
					.setParameter("k", k)
					.getResultList();
			for(int i=0; i<akList.size(); i++)
				em.refresh(akList.get(i));
			for(AutorKnjiga ak : akList) {
				result.add(ak.getAutor());
			}
		} catch(NoResultException nre) {}
		return result;
	}

}
