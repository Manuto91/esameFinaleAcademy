package it.demo.entity;


public class CategoriaDTO {

	private String categoria;
	
	public CategoriaDTO() {
}

	public CategoriaDTO(String categoria) {
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

