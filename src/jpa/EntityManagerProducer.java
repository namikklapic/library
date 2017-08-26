package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import swing.PanelPrijava;
import util.*;

public class EntityManagerProducer<T> {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Library");
	protected EntityManager em = emf.createEntityManager();
	
	public T save(T entity){
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		return entity;
	}
}