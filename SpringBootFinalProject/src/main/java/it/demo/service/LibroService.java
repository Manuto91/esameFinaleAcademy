package it.demo.service;

import java.util.List;

import it.demo.dto.AutoreDTO;
import it.demo.dto.LibroDTO;
import it.demo.entity.Autore;
import it.demo.entity.Categoria;
import it.demo.entity.CategoriaDTO;
import it.demo.entity.Libro;
import it.demo.entity.StockElement;


public interface LibroService {

	public Libro registraNuovoLibro(Libro l) throws Exception;

	public Libro modificaDatiLibro(Libro l) throws Exception;

	public void eliminaLibro(int isbn) throws Exception;

	public void modificaNumeroCopieLibro(int isbn, int nCopie) throws Exception;

	public Libro leggiDatiLibro(int isbn) throws Exception;
	public Autore getAutore(int isbn);






	//travasi:
	public Libro travasoDaDTOANorm(LibroDTO librodto);
	public LibroDTO travasoDaNormADTO(Libro libro);

	//travasi in più(?)
	public AutoreDTO travasoDaNormaleADTOAutore(Autore autore);

	public	Autore travasoDaDTOANormaleAutore(AutoreDTO autoreDTO);

	public CategoriaDTO travasoDaNormaleADTOCategoria(Categoria categoria);

	public Categoria travasoDaDTOANormaleCategoria(CategoriaDTO categoriaDTO);

	//metodi Stock

	public List<StockElement> apriRichiesta(int isbn, int nCopie) throws Exception;

	public List<StockElement> cambiaStatoRichiesta(int idStock) throws Exception;

	public List<StockElement> leggiInfoStock(int isbn) throws Exception;

	public List<StockElement> chiudiRichiesta(int isbn) throws Exception;

	public List<StockElement> modificaCopie(int idStock, int nCopie) throws Exception;

	//metodi aggiuntivi

	public List<Libro> getAll();
	public List<Autore> getAllAutori();

	public List<Libro> getAllLibriCategoria(String categoria);

	public List<Libro> getAllLibriPerNomeAutore(String nomeCognome) throws Exception;

	public List<Categoria> getAllCategorie();
}



