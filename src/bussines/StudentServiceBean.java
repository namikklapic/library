package bussines;

import java.util.List;

import javax.persistence.NoResultException;

import jpa.EntityManagerProducer;
import jpa.Student;

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
			find.setUspisaniSemestar(entity.getUspisaniSemestar());
			em.getTransaction().commit();
		} else {
			super.save(entity);
		}
		return entity;
	}

}
