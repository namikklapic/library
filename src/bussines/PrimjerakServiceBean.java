package bussines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import jpa.Knjiga;
import jpa.Literatura;
import jpa.Posudba;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import jpa.AutorKnjiga;
import jpa.EntityManagerProducer;
import jpa.Primjerak;
import swing.PanelPrijava;
import util.MyEvent;

class PrimjerakComparator implements Comparator<Primjerak> {
    @Override
    public int compare(Primjerak o1, Primjerak o2) {
        return o1.getKnjiga().getNaslov().compareToIgnoreCase(o2.getKnjiga().getNaslov());
    }
}

public class PrimjerakServiceBean extends EntityManagerProducer<Primjerak> {
	
	
	public Primjerak getByInvBroj(String invBroj) {
		Primjerak result = null;
		try {
			result = (Primjerak)em
					.createQuery("Select p from Primjerak p where p.inventarskiBroj=:invBroj")
					.setParameter("invBroj", invBroj)
					.getSingleResult();
			em.refresh(result);
		} catch(NoResultException nre) {}

		return result;
	}
	
	public List<Primjerak> getAllAvailablePrimjerakByBook(Knjiga k) {
		List<Primjerak> result = null;
		try {
			Query query = em.createQuery("select p from Primjerak p where p.knjiga=:k and p.posudjen=0 and p.rezervisan=0");
			query.setParameter("k",k);
			result = query.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new PrimjerakComparator());
		} catch(NoResultException nre) {}

		return result;
	}
	
	public List<Knjiga> getAllAvailableKnjige() {
		List<Knjiga> result = null;
		List<Knjiga> available = new ArrayList<Knjiga>();
		try {
			Query query = em.createQuery("select p.knjiga from Primjerak p where p.posudjen=0 and p.rezervisan=0");
			result = query.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			for (Knjiga knjiga: result)
			{
			  if (!available.contains(knjiga)) 
			  {
				  available.add(knjiga);
			  }
			}
			Collections.sort(available, new KnjigaComparator());
		} catch(NoResultException nre) {}
		return available;	
	}
	
	public List<Knjiga> getAllAvailableKnjigeByNaslov(String naslov) {
		List<Knjiga> result = null;
		List<Knjiga> available = new ArrayList<Knjiga>();
		try {
			Query query = em.createQuery("select p.knjiga from Primjerak p where p.posudjen=0 and p.rezervisan=0");
			result = query.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			for (Knjiga knjiga: result)
			{
			  if (!available.contains(knjiga) && knjiga.getNaslov() == naslov) 
			  {
				  available.add(knjiga);
			  }
			}
			Collections.sort(available, new KnjigaComparator());
		} catch(NoResultException nre) {}
		return available;	
	}
	
	public List<Knjiga> getAllAvailableKnjigeByIzdavac(String izdavac) {
		List<Knjiga> result = null;
		List<Knjiga> available = new ArrayList<Knjiga>();
		try {
			Query query = em.createQuery("select p.knjiga from Primjerak p where p.posudjen=0 and p.rezervisan=0");
			result = query.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			for (Knjiga knjiga: result)
			{
			  if (!available.contains(knjiga) && knjiga.getIzdavac().getNazivIzdavaca() == izdavac) 
			  {
				  available.add(knjiga);
			  }
			}
			Collections.sort(available, new KnjigaComparator());
		} catch(NoResultException nre) {}
		return available;	
	}
	
	public List<Knjiga> getAllAvailableKnjigeByVrsta(String vrsta){
		List<Knjiga> result = null;
		List<Knjiga> available = new ArrayList<Knjiga>();
		try{
			result = em.createQuery("Select p.knjiga from Primjerak p where p.posudjen=0 and p.rezervisan=0")
					.getResultList();
			for(int i = 0; i < result.size(); i++)
				em.refresh(result.get(i));
			for(Knjiga k : result){
				if(!available.contains(k) && k.getVrsta().getNazivVrste().equals(vrsta))
					available.add(k);
			}
			Collections.sort(available, new KnjigaComparator());
		}catch(NoResultException nre){}
		
		return available;
	}

	
	public List<Knjiga> getAllAvailableKnjigeByAutor(String ime, String prezime) {
		List<Knjiga> result = null;
		List<AutorKnjiga> lista = null;
		List<Knjiga> available = new ArrayList<Knjiga>();
		
		try{
			lista = em.createQuery("Select ak from AutorKnjiga ak where ak.autor.imeAutora=:ime and ak.autor.prezimeAutora=:prezime")
					.setParameter("ime", ime)
					.setParameter("prezime", prezime)
					.getResultList();
			for(int i=0; i<lista.size(); i++)
				em.refresh(lista.get(i));
		}catch(NoResultException nre) {}
		
		try {
			Query query = em.createQuery("select p.knjiga from Primjerak p where p.posudjen=0 and p.rezervisan=0");
			result = query.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			for (Knjiga knjiga: result)
			{
			  if (!available.contains(knjiga)) 
			  {
				  for(int i=0; i<lista.size(); i++){
					  if(lista.get(i).getKnjiga() == knjiga) {
						  available.add(knjiga);
						  break;
					  }
				  }
			  }
			}
			Collections.sort(available, new KnjigaComparator());
		} catch(NoResultException nre) {}
		return available;	
	}
	
	public List<Knjiga> getAllAvailableKnjigeByPredmet(String predmet) {
		List<Knjiga> result = null;
		List<Literatura> lista = null;
		List<Knjiga> available = new ArrayList<Knjiga>();
		
		try{
			lista = em.createQuery("Select l from Literatura l where l.predmet.nazivPredmeta=:predmet")
					.setParameter("predmet", predmet)
					.getResultList();
			for(int i=0; i<lista.size(); i++)
				em.refresh(lista.get(i));
		}catch(NoResultException nre) {}
		
		try {
			Query query = em.createQuery("select p.knjiga from Primjerak p where p.posudjen=0 and p.rezervisan=0");
			result = query.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			for (Knjiga knjiga: result)
			{
			  if (!available.contains(knjiga)) 
			  {
				  for(int i=0; i<lista.size(); i++){
					  if(lista.get(i).getKnjiga() == knjiga) {
						  available.add(knjiga);
						  break;
					  }
				  }
			  }
			}
			Collections.sort(available, new KnjigaComparator());
		} catch(NoResultException nre) {}
		return available;	
	}
	
	
	public List<Primjerak> getAllPrimjerak(){
		List<Primjerak> result = null;
		try {
			result = em.createQuery("select p from Primjerak p").getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new PrimjerakComparator());
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
		MyEvent evt = new MyEvent(this, "Update Primjerak");
		PanelPrijava.realTime.fireMyEvent(evt);
		return entity;
	}
	
	public void setPrimjerakPosudjen(Primjerak entity, boolean state){
		Primjerak find = em.find(Primjerak.class, entity.getInventarskiBroj());
		if(find != null){
			em.getTransaction().begin();
			find.setPosudjen(state);
			em.getTransaction().commit();
			MyEvent evt = new MyEvent(this, "Update Primjerak");
			PanelPrijava.realTime.fireMyEvent(evt);
		}
	}
	
	public void setPrimjerakRezervisan(Primjerak entity, boolean state){
		Primjerak find = em.find(Primjerak.class, entity.getInventarskiBroj());
		if(find != null){
			em.getTransaction().begin();
			find.setRezervisan(state);
			em.getTransaction().commit();
			MyEvent evt = new MyEvent(this, "Update Primjerak");
			PanelPrijava.realTime.fireMyEvent(evt);
		}
	}
	
	public void deleteByKnjiga(Knjiga k){
		try{
			em.createQuery("Delete from Primjerak p where p.knjiga=:k")
			.setParameter("k", k);
		}catch(Exception e) {}
	}
}
