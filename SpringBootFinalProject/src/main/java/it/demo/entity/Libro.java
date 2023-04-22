package it.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Libro {
	@Id
	private int isbn;
	private String titolo;
	private String descrizione;
	private double prezzo;
	private int nCopie;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "fk_autore")
	private Autore autore;
	@ManyToOne
	@JoinColumn(name = "fk_categoria")
	private Categoria categoria;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_libro")
	private List<StockElement> riordini;
	
	public Libro() {
}

	public Libro(int isbn, String titolo, String descrizione, double prezzo, int nCopie, Autore autore,
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
