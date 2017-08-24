package bussines;

import java.util.List;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
import jpa.Student;
import swing.PanelPrijava;
import util.MyEvent;

public class StudentServiceBean extends EntityManagerProducer<Student> {
	
	public Student findByIndexNumber(String brojIndeksa, String password){
		Student result = null;
		try{
			result = (Student)em
					.createQuery("select s from Student s where s.brojIndeksa=:brojIndeksa and s.password=:password")
					.setParameter("brojIndeksa", brojIndeksa)
					.setParameter("password", password)
					.getSingleResult();
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
		}catch(NoResultException nre) { }
		
		return result;
	}
	
	public List<Student> getStudentByIndexNumber(String index){
		List<Student> result = null;
		try{
			result = em.createQuery("Select s from Student s where s.brojIndeksa=:brojIndeksa")
					.setParameter("brojIndeksa", index)
					.getResultList();
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
		}catch(NoResultException nre){}
		
		return result;
	}
	
	public List<Student> getStudentByNegativePoints(int bodovi){
		List<Student> result = null;
		try{
			result = em.createQuery("Select s from Student s inner join s.korisnik k where k.brojNegativnihBodova=:bodovi")
					.setParameter("bodovi", bodovi)
					.getResultList();
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
		}catch(NoResultException nre){}
		
		return result;
	}
	
	public boolean existsStudent(String jmbg){
		if(searchByJMBG(jmbg) != null)
			return true;
		return false;
	}
	
	public List<Student> getAllStudent(){
		List<Student> result = null;
		try {
			result = em.createQuery("Select s from Student s").getResultList();
		} catch(NoResultException nre) {}
		return result;
	}
	
	public Student save(Student entity) {
		Student find = em.find(Student.class, entity.getBrojIndeksa());
		if (find != null) {
			em.getTransaction().begin();
			find.setBrojIndeksa(entity.getBrojIndeksa());
			find.setKorisnik(entity.getKorisnik());
			find.setPassword(entity.getPassword());
			find.setUpisaniSemestar(entity.getUpisaniSemestar());
			em.getTransaction().commit();
		} else {
			super.save(entity);
		}
		MyEvent evt = new MyEvent(this, "Update Student");
		PanelPrijava.realTime.fireMyEvent(evt);
		return entity;
	}
}
