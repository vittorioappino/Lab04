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
				return null;
			}
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Corso> getCorsiByStudente(Studente studente) {
		String sql = "SELECT * "
				+ "FROM corso c, iscrizione i "
				+ "WHERE c.codins = i.codins "
				+ "AND i.matricola = ?";
		List<Corso> result = new LinkedList<Corso>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Corso c= new Corso(rs.getString("codins"), 
						rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				result.add(c);
			}
			
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	public boolean isStudenteIscrittoACorso(Studente studente, Corso corso) {
		final String sql = "SELECT * FROM iscrizione where codins=? and matricola=?";
		boolean returnValue = false;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			st.setInt(2, studente.getMatricola());

			ResultSet rs = st.executeQuery();

			if (rs.next())
				returnValue = true;

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}

		return returnValue;
	}

}
