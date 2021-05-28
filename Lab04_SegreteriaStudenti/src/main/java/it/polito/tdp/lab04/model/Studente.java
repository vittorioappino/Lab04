package it.polito.tdp.lab04.model;

public class Studente {
	
	private String nome;
	private String cognome;
	private String CDS;
	private Integer matricola;
	
	public Studente(String nome, String cognome, String cDS, Integer matricola) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		CDS = cDS;
		this.matricola = matricola;
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

	public String getCDS() {
		return CDS;
	}

	public void setCDS(String cDS) {
		CDS = cDS;
	}

	public Integer getMatricola() {
		return matricola;
	}

	public void setMatricola(Integer matricola) {
		this.matricola = matricola;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricola == null) ? 0 : matricola.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		if (matricola == null) {
			if (other.matricola != null)
				return false;
		} else if (!matricola.equals(other.matricola))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("%-8s %-20s %-20s %-8s", matricola, nome, cognome, CDS);
	}
	
	

}
