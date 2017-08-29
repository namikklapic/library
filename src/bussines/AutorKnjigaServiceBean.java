package bussines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.NoResultException;

import jpa.Autor;
import jpa.AutorKnjiga;
import jpa.EntityManagerProducer;
import jpa.Knjiga;
import jpa.Posudba;
import swing.PanelPrijava;
import util.MyEvent;

class AutorKnjigaComparator implements Comparator<AutorKnjiga> {
    @Override
    public int compare(AutorKnjiga o1, AutorKnjiga o2) {
    	Integer i1,i2;
    	i1 = o1.getRedniBrojAutoraNaKnjizi();
    	i2 = o2.getRedniBrojAutoraNaKnjizi();
        return i1.compareTo(i2);
    }
}

public class AutorKnjigaServiceBean extends EntityManagerProducer<AutorKnjiga> {
	
	public AutorKnjiga save(AutorKnjiga entity) {
		AutorKnjiga find = em.find(AutorKnjiga.class, entity.getId());
		
		if(find != null) {
			em.getTransaction().begin();
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
			//em.refresh(lista);
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
			Collections.sort(result, new KnjigaComparator());
		}catch(NoResultException nre) {}
		
		if(lista != null){
			for(AutorKnjiga ak : lista)
				result.add(ak.getKnjiga());
		}
		return result;
	}
	
	public List<Knjiga> getKnjigeByAutorFilter(String filter){
		String[] spliter = filter.split(" ");
		List<AutorKnjiga> lista = new ArrayList<AutorKnjiga>();
		List<AutorKnjiga> temp = null;
		List<Knjiga> result = new ArrayList<Knjiga>();
		for(int brojRijeci=0; brojRijeci < spliter.length; brojRijeci++) {
			String fil = "%" + spliter[brojRijeci] + "%";
			try{
				temp = em.createQuery("Select ak from AutorKnjiga ak where ak.autor.imeAutora LIKE :fil or ak.autor.prezimeAutora LIKE :fil")
					.setParameter("fil", fil)
					.getResultList();
				for(int i=0; i<temp.size(); i++) {
					em.refresh(temp.get(i));
					if(!lista.contains(temp.get(i)))
						lista.add(temp.get(i));
				}
			}catch(NoResultException nre) {}
		
		if(lista != null){
			for(AutorKnjiga ak : lista) {
				if(!result.contains(ak.getKnjiga()))
					result.add(ak.getKnjiga());
			}
		}
		}
		Collections.sort(result, new KnjigaComparator());
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
			Collections.sort(result, new AutorComparator());
		} catch(NoResultException nre) {}
		return result;
	}

}
