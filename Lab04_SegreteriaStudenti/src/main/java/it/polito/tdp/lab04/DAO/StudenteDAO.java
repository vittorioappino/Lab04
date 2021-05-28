package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente esisteStudente(Integer matricola) {
		String sql = "SELECT * FROM studente WHERE matricola = ?";
		Studente studente = null;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String CDS = rs.getString("CDS");
				studente = new Studente(nome, cognome, CDS, matricola);
				rs.close();
				st.close();
				conn.close();
				return studente;
			} else{
				rs.close();
				st.close();
				conn.close();
				studente = new Studente(null, null, null, matricola);
				return studente;
			}
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
