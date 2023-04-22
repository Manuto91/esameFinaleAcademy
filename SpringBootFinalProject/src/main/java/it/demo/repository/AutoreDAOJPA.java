package it.demo.repository;

import org.springframework.stereotype.Repository;

import it.demo.entity.Autore;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Repository
public class AutoreDAOJPA implements AutoreDAO {

	@PersistenceContext
	EntityManager manager;

	@Override
	public Autore selectAutorebyId(int id) {
		return manager.find(Autore.class, id);

	}

}
