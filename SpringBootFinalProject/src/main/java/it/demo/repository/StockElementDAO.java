package it.demo.repository;

import it.demo.entity.StockElement;

public interface StockElementDAO {

	public void insert(StockElement s);

	public boolean delete(int id);

	public StockElement select(int id);
}
