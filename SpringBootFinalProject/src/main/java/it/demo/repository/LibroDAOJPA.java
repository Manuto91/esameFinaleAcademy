package it.demo.repository;

import java.util.List;


import org.springframework.stereotype.Repository;

import it.demo.entity.Autore;
import it.demo.entity.Categoria;
import it.demo.entity.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


@Repository
public class LibroDAOJPA implements LibroDAO {
	@PersistenceContext
	private EntityManager manager;
	
	


	@Override
	public void insert(Libro l) {
		manager.persist(l);

	}

	@Override
	public Libro selectByisbn(int isbn) {
		return manager.find(Libro.class, isbn);

	}

	@Override
	public void cancellaLibro(int isbn) {

		Libro libro = manager.find(Libro.class, isbn);
		if (libro != null) {
			manager.remove(libro);
		}

	}

	@Override
	public List<Libro> selectAll() {
		
		TypedQuery<Libro> tq = manager.createQuery("select l from Libro l",Libro.class);
		List<Libro> libri =  tq.getResultList();
		return libri;
	}

	@Override
	public Autore selectAutoreByNomeECognome(String nome, String cognome) {
		return null;
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public Categoria selectCategoriaById(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Libro getLibroByStockId(int idStock) {

		TypedQuery<Libro> tq = manager.createQuery("select l from Libro l join l.riordini s where s.id=?1 ",
				Libro.class);
		tq.setParameter(1, idStock);
		return tq.getSingleResult(); //se la query ritorna una lista scoppia tutto, quindi singleResult


	}
	
	@Override
	public List<Autore> getAllAutori() {
		TypedQuery<Autore> tq = manager.createQuery("select a from Autore a",Autore.class);
		List<Autore> autori =  tq.getResultList();
		return autori;
	}


	@Override
	public List<Libro> getAllLibriCategoria(String categoria) {
		TypedQuery<Libro> tq = manager.createQuery("select l from Libro l join l.categoria s where s.categoria=?1 ",Libro.class);
		tq.setParameter(1, categoria);
		List<Libro> l = tq.getResultList();
		return l;
	}

	@Override
//	public List<Libro> getAllLibriPerNomeAutore(String nome, String cognome) {
//		
//		String query="select l from Libro l join l.autore s where ";
//
//		boolean nomeEsiste=nome!=null && !nome.trim().isEmpty();
//		boolean cognomeEsiste=cognome!=null && !cognome.trim().isEmpty();
//
//		if(nomeEsiste) {
//			query+="s.nome=:nomeAutore ";
//			if(cognomeEsiste) {
//				query+="AND ";
//			}
//		}
//		if(cognomeEsiste) {
//			query+="s.cognome=:cognomeAutore ";
//		}
//
//		TypedQuery<Libro> tq = manager.createQuery(query, Libro.class);
//
//
//		if(nomeEsiste) {
//			tq.setParameter("nomeAutore",nome);
//		}
//		if(cognomeEsiste) {
//			tq.setParameter("cognomeAutore", cognome);
//		}
//
//		List<Libro> l = tq.getResultList();
//		return l;
//	}
	public List<Libro> getAllLibriPerNomeAutore(String nomeCognome) {

		String query="select l from Libro l join l.autore s where s.nome=?1 OR s.cognome=?2";



		TypedQuery<Libro> tq = manager.createQuery(query, Libro.class);


		tq.setParameter(1,nomeCognome);

		tq.setParameter(2, nomeCognome);


		List<Libro> l = tq.getResultList();
		return l;
	}
		
//	select l from Libro l join l.autore s where s.nome=?1 AND s.cognome=?2

	@Override
	public List<Categoria> getAllCategorie() {
		TypedQuery<Categoria> tq = manager.createQuery("select c from Categoria c",Categoria.class);
		List<Categoria> l = tq.getResultList();
		return l;
	}
}


