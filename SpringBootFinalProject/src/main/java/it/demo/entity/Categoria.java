package it.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Categoria {
	@Id
	private String categoria;
	
	public Categoria() {
}

	public Categoria(String categoria) {
		super();
		this.categoria = categoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Categoria [categoria=" + categoria + "]";
	}
	
}
