package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO CorsoDao;
	private StudenteDAO StudenteDao;
	public List<Corso> corsi = null;
	
	public Model() {
		CorsoDao =new CorsoDAO();
		StudenteDao = new StudenteDAO(); 
	}

	public List<Corso> getTuttiICorsi() {
		if(this.corsi==null) {
			this.corsi = CorsoDao.getTuttiICorsi();
		}
		return this.corsi;
	}
	
	public Studente esisiteStudente(Integer matricola) {
		return StudenteDao.esisteStudente(matricola);
	}

}
