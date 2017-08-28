package bussines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.NoResultException;

import jpa.Autor;
import jpa.EntityManagerProducer;
import jpa.Izdavac;
import jpa.Literatura;
import jpa.Nastavnik;
import swing.PanelPrijava;
import util.MyEvent;

class NastavnikComparator implements Comparator<Nastavnik> {
    @Override
    public int compare(Nastavnik o1, Nastavnik o2) {
        return o1.getKorisnik().getImeKorisnika().compareToIgnoreCase(o2.getKorisnik().getImeKorisnika());
    }
}

public class NastavnikServiceBean extends EntityManagerProducer<Nastavnik>{
	
	public Nastavnik findByNameAndSurname(String nameAndSurname, String password){
		Nastavnik result = null;		
		try{
			String ime = nameAndSurname.split("[.]")[0];
			String prezime = nameAndSurname.split("[.]")[1];
			result = (Nastavnik)em
					.createQuery("select n from Nastavnik n inner join  n.korisnik k where k.imeKorisnika=:ime and k.prezimeKorisnika=:prezime and n.password=:password")
					.setParameter("ime",ime )
					.setParameter("prezime", prezime)
					.setParameter("password", password)
					.getSingleResult();
			em.refresh(result);
		} catch(NoResultException nre){
			
		} catch(ArrayIndexOutOfBoundsException err){
			
		}
		return result;
	}
	
	public List<Nastavnik> getNastavniciByFullName(String ime, String prezime){
		List<Nastavnik> result = null;
		try{
			result = em.createQuery("Select n from Nastavnik n inner join n.korisnik k where k.imeKorisnika=:ime and k.prezimeKorisnika=:prezime")
					.setParameter("ime", ime)
					.setParameter("prezime", prezime)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new NastavnikComparator());
		}catch(NoResultException nre){}
		return result;
	}
	
	public Nastavnik searchByJMBG(String sifra){
		Nastavnik result = null;
		try{
			result = (Nastavnik)em
					.createQuery("Select n from Nastavnik n inner join n.korisnik k where k.sifra=:sifra")
					.setParameter("sifra", sifra)
					.getSingleResult();
			em.refresh(result);
		}catch(NoResultException nre) {}
		
		return result;
	}
	
	public boolean existsNastavnik(String jmbg){
		if(searchByJMBG(jmbg) != null)
			return true;
		return false;
	}
	
	public Integer getCount() {
		Long c = (long) 0;
		try {
			c = (Long)em.createQuery("select count(i) from Nastavnik i").getSingleResult();
		} catch(NoResultException nre) {}
		return (Integer) c.intValue();
	}
	
	public Nastavnik save(Nastavnik entity) {
		Nastavnik find = em.find(Nastavnik.class, entity.getKorisnik().getSifraKorisnika());
		if (find != null) {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		} else {
			super.save(entity);
		}
		MyEvent evt = new MyEvent(this, "Update Nastavnik");
		PanelPrijava.realTime.fireMyEvent(evt);
		return entity;
	}
	
	/**
	 * 
	 * @return List of all Nastavnik in database
	 */
	public List<Nastavnik> getAllNastavnik() {
		List<Nastavnik> result = null;
		try {
			result = em.createQuery("select n from Nastavnik n").getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new NastavnikComparator());
		} catch(NoResultException nre) {}
		return result;
	}

	public List<Nastavnik> getNastavniciByFilter(String filter) {
		String[] spliter = filter.split(" ");
		List<Nastavnik> result = new ArrayList<Nastavnik>();
		List<Nastavnik> temp = new ArrayList<Nastavnik>();
		for(int brojRijeci=0; brojRijeci < spliter.length; brojRijeci++) {
			String fil = "%" + spliter[brojRijeci] + "%";
			try{
				temp = em.createQuery("Select n from Nastavnik n inner join n.korisnik k where k.imeKorisnika LIKE :fil or k.prezimeKorisnika LIKE :fil")
					.setParameter("fil", fil)
					.getResultList();
				for(int i=0; i<temp.size(); i++) {
					em.refresh(temp.get(i));
					if(!result.contains(temp.get(i)))
						result.add(temp.get(i));
				}
			}catch(NoResultException nre){}
		}
		Collections.sort(result, new NastavnikComparator());
		return result;
	}
	
}
