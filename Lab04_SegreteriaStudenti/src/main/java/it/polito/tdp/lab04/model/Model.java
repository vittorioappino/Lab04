package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;

public class Model {
	
	public List<Corso> corsi = null;

	public List<Corso> getCorsi() {
		if(this.corsi==null) {
			CorsoDAO dao = new CorsoDAO();
			this.corsi = dao.getTuttiICorsi();
		}
		return this.corsi;
	}

}
