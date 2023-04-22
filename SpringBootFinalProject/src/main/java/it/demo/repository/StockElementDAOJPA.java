package it.demo.repository;

import org.springframework.stereotype.Repository;

import it.demo.entity.StockElement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Repository
public class StockElementDAOJPA implements StockElementDAO {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void insert(StockElement s) {
		manager.persist(s);
	}

	@Override
	public boolean delete(int id) {
		StockElement stock = manager.find(StockElement.class, id);
		if(stock!= null) {

			manager.remove(stock);
			return true;
		}
		return false;
	}

	@Override
	public StockElement select(int id) {
		return manager.find(StockElement.class, id);
	}
}
