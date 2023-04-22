package it.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StockElement {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int quantita;
	private String statoStock;

	public StockElement() {
}
	public StockElement(int quantita, String statoStock) {
		this.quantita=quantita;
		this.statoStock=statoStock;
	}

	public StockElement(int id, int quantita, String statoStock) {
		super();
		this.id = id;
		this.quantita = quantita;
		this.statoStock = statoStock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getStatoStock() {
		return statoStock;
	}

	public void setStatoStock(String statoStock) {
		this.statoStock = statoStock;
	}

	@Override
	public String toString() {
		return "StockElement [id=" + id + ", quantita=" + quantita + ", statoStock=" + statoStock + "]";
	}
	
	
	
}
