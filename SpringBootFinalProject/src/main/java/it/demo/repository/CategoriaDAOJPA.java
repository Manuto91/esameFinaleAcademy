package it.demo.repository;

import org.springframework.stereotype.Repository;

import it.demo.entity.Categoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Repository
public class CategoriaDAOJPA implements CategoriaDAO {

	@PersistenceContext
	EntityManager manager;
	@Override
	public Categoria selectCategoriaById(String id) {
		return manager.find(Categoria.class, id);

	}

}
