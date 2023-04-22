package it.demo.repository;

import java.util.List;

import it.demo.entity.Autore;
import it.demo.entity.Categoria;
import it.demo.entity.Libro;
import jakarta.persistence.TypedQuery;

public interface LibroDAO {



	public void insert(Libro l);

	public Libro selectByisbn(int isbn);

	public void cancellaLibro(int isbn);
	public Libro getLibroByStockId(int idStock);



	public Autore selectAutoreByNomeECognome(String nome, String cognome);
	public Categoria selectCategoriaById(String string);
	public List<Libro> selectAll();

	public List<Autore> getAllAutori();

	public List<Libro> getAllLibriCategoria(String categoria);

	public List<Libro> getAllLibriPerNomeAutore(String nomeCognome);

	public List<Categoria> getAllCategorie();
}

