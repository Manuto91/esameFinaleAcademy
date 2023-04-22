package it.demo.dto;

import java.util.List;

import it.demo.entity.Autore;
import it.demo.entity.Categoria;
import it.demo.entity.StockElement;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class LibroDTO {
	
	private int isbn;
	private String titolo;
	private String descrizione;
	private double prezzo;
	private int nCopie;
	
	private Autore autore;
	
	private Categoria categoria;
	
	private List<StockElement> riordini;
	
	public LibroDTO(){}

	public LibroDTO(int isbn, String titolo, String descrizione, double prezzo, int nCopie, Autore autore,
			Categoria categoria, List<StockElement> riordini) {
		super();
		this.isbn = isbn;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.nCopie = nCopie;
		this.autore = autore;
		this.categoria = categoria;
		this.riordini = riordini;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getnCopie() {
		return nCopie;
	}

	public void setnCopie(int nCopie) {
		this.nCopie = nCopie;
	}

	public Autore getAutore() {
		return autore;
	}

	public void setAutore(Autore autore) {
		this.autore = autore;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<StockElement> getRiordini() {
		return riordini;
	}

	public void setRiordini(List<StockElement> riordini) {
		this.riordini = riordini;
	}

	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", titolo=" + titolo + ", descrizione=" + descrizione + ", prezzo=" + prezzo
				+ ", nCopie=" + nCopie + ", autore=" + autore + ", categoria=" + categoria + ", riordini=" + riordini
				+ "]";
	}
	
}

