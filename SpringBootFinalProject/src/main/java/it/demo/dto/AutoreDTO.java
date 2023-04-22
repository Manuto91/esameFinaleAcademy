package it.demo.dto;

public class AutoreDTO {
	
	private int id;
	private String nome;
	private String cognome;
	private String nazioneResidenza;
	
	public AutoreDTO() {}
	
	public AutoreDTO(int id, String nome, String cognome, String nazioneResidenza) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.nazioneResidenza = nazioneResidenza;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNazioneResidenza() {
		return nazioneResidenza;
	}
	public void setNazioneResidenza(String nazioneResidenza) {
		this.nazioneResidenza = nazioneResidenza;
	}
	@Override
	public String toString() {
		return "Autore [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", nazioneResidenza=" + nazioneResidenza
				+ "]";
	}
	
	
}
