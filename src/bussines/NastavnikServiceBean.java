package bussines;

import java.util.List;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
import jpa.Izdavac;
import jpa.Nastavnik;
import swing.PanelPrijava;
import util.MyEvent;

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
			find.setAkademskoZvanje(entity.getAkademskoZvanje());
			find.setKorisnik(entity.getKorisnik());
			find.setPassword(entity.getPassword());
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
		} catch(NoResultException nre) {}
		return result;
	}
	
}
