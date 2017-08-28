package bussines;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
import jpa.Nastavnik;
import jpa.Student;
import swing.PanelPrijava;
import util.MyEvent;

class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getKorisnik().getImeKorisnika().compareToIgnoreCase(o2.getKorisnik().getImeKorisnika());
    }
}

public class StudentServiceBean extends EntityManagerProducer<Student> {
	
	public Student findByIndexNumber(String brojIndeksa, String password){
		Student result = null;
		try{
			result = (Student)em
					.createQuery("select s from Student s where s.brojIndeksa=:brojIndeksa and s.password=:password")
					.setParameter("brojIndeksa", brojIndeksa)
					.setParameter("password", password)
					.getSingleResult();
			em.refresh(result);
		} catch(NoResultException nre){
			
		}
		return result;
	}
	
	public Student searchByIndexNumber(String brojIndeksa){
		Student result = null;
		try{
			result = (Student)em
					.createQuery("Select s from Student s where s.brojIndeksa=:brojIndeksa")
					.setParameter("brojIndeksa", brojIndeksa)
					.getSingleResult();
			em.refresh(result);
		}catch(NoResultException nre) { }

		return result;
	}
	
	public List<Student> getStudentByIndexNumber(String index){
		List<Student> result = null;
		index = "%" + index + "%";
		try{
			result = em.createQuery("Select s from Student s where s.brojIndeksa LIKE :index")
					.setParameter("index", index)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new StudentComparator());
		}catch(NoResultException nre){}

		return result;
	}
	
	public List<Student> getStudentByFullName(String ime, String prezime){
		List<Student> result = null;
		try{
			result = em.createQuery("Select s from Student s inner join s.korisnik k where k.imeKorisnika=:ime and k.prezimeKorisnika=:prezime")
					.setParameter("ime", ime)
					.setParameter("prezime", prezime)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new StudentComparator());
		}catch(NoResultException nre){}

		return result;
	}
	
	public List<Student> getStudentByNegativePoints(int bodovi){
		List<Student> result = null;
		try{
			result = em.createQuery("Select s from Student s inner join s.korisnik k where k.brojNegativnihBodova=:bodovi")
					.setParameter("bodovi", bodovi)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new StudentComparator());
		}catch(NoResultException nre){}

		return result;
	}
	
	public Student searchByJMBG(String jmbg){
		Student result = null;
		try{
			result = (Student)em
					.createQuery("Select s from Student s inner join s.korisnik k where k.sifra=:jmbg")
					.setParameter("jmbg", jmbg)
					.getSingleResult();
			em.refresh(result);
		}catch(NoResultException nre){}

		return result;
	}
	
	public boolean existsStudent(String jmbg){
		if(searchByJMBG(jmbg) != null)
			return true;
		return false;
	}
	
	public boolean existsStudentByIndexNum(String index){
		if(searchByIndexNumber(index) != null)
			return true;
		return false;
	}
	
	public List<Student> getAllStudent(){
		List<Student> result = null;
		try {
			result = em.createQuery("Select s from Student s").getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new StudentComparator());
		} catch(NoResultException nre) {}

		return result;
	}
	
	public Student save(Student entity) {
		Student find = em.find(Student.class, entity.getBrojIndeksa());
		if (find != null) {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		} else {
			super.save(entity);
		}
		MyEvent evt = new MyEvent(this, "Update Student");
		PanelPrijava.realTime.fireMyEvent(evt);
		return entity;
	}

	public List<Student> getStudentByFilter(String filter) {
		List<Student> result = null;
		filter = "%" + filter + "%";
		try{
			result = em.createQuery("Select s from Student s inner join s.korisnik k where k.imeKorisnika LIKE :filter or k.prezimeKorisnika LIKE :filter")
					.setParameter("filter", filter)
					.getResultList();
			for(int i=0; i<result.size(); i++)
				em.refresh(result.get(i));
			Collections.sort(result, new StudentComparator());
		}catch(NoResultException nre){}

		return result;
	}
}
