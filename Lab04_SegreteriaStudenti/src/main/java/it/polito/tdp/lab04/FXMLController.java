package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model Model;
	private List<Corso> corsi;
	private Studente studente;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> comboCorso;

    @FXML
    private Button btnCercaIscrittiCorso;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnCercaNome;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCerca;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtResult.clear();
		txtNome.clear();
		txtCognome.clear();

		try {

			int matricola = Integer.parseInt(txtMatricola.getText());
			Studente studente = Model.esisiteStudente(matricola);

			if (studente == null) {
				txtResult.appendText("Nessun risultato: matricola inesistente");
				return;
			}
 
			List<Corso> corsi = Model.getCorsiByStudente(studente);
			
			if(corsi.size() == 0) {
	    		txtResult.appendText("Lo studente non è isccritto ad alcun corso");
	    		return;
	    	}
			
			StringBuilder sb = new StringBuilder();
	    	for(Corso c : corsi) {
	    		sb.append(String.format("%-8s ", c.getCodins()));
	    		sb.append(String.format("%-4d ", c.getNumeroCrediti()));
	    		sb.append(String.format("%-50s ", c.getNome()));
	    		sb.append(String.format("%-4d\n", c.getPeriodoDidattico()));
	    	}
	    	
	    	txtResult.appendText(sb.toString());

		} catch (NumberFormatException e) {
			txtResult.setText("Inserire una matricola nel formato corretto.");
		} catch (RuntimeException e) {
			txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}


    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	txtResult.clear();
    try {
    	
    	Corso corso = comboCorso.getValue();
    	if(corso==null) {
    		txtResult.setText("Selezionare un corso");
    		return;
    	}
    	
    	List<Studente> studenti = Model.getStudentiByCorso(corso);
    	
    	if(studenti.size() == 0) {
    		txtResult.appendText("Il corso non ha iscritti");
    		return;
    	}
    	
    	StringBuilder sb = new StringBuilder();

    	for(Studente s : studenti) {
    		txtResult.appendText(s + "\n");
    	}
    }catch(RuntimeException e) {
    	txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
    }
    }

    @FXML
    void doCercaNome(ActionEvent event) {
    	txtResult.clear();
		txtNome.clear();
		txtCognome.clear();

		try {

			int matricola = Integer.parseInt(txtMatricola.getText());
			Studente studente = Model.esisiteStudente(matricola);

			if (studente == null) {
				txtResult.appendText("Nessun risultato: matricola inesistente");
				return;
			}

			txtNome.setText(studente.getNome());
			txtCognome.setText(studente.getCognome());

		} catch (NumberFormatException e) {
			txtResult.setText("Inserire una matricola nel formato corretto.");
		} catch (RuntimeException e) {
			txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}

    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	txtResult.clear();

		try {

			if (txtMatricola.getText().isEmpty()) {
				txtResult.setText("Inserire una matricola.");
				return;
			}

			if (comboCorso.getValue() == null) {
				txtResult.setText("Selezionare un corso.");
				return;
			}

			int matricola = Integer.parseInt(txtMatricola.getText());

			Studente studente = Model.esisiteStudente(matricola);
			if (studente == null) {
				txtResult.appendText("Nessun risultato: matricola inesistente");
				return;
			}

			txtNome.setText(studente.getNome());
			txtCognome.setText(studente.getCognome());

			Corso corso = comboCorso.getValue();

			if (Model.isStudenteIscrittoACorso(studente, corso)) {
				txtResult.appendText("Studente già iscritto a questo corso");
				return;
			}
			// Prima di passare a rendere il tasto 'Iscrivi' realmente operativo 
			// con l'iscrizione, la versione 'Cerca' avrebbe fatto solo il successivo else.
			//}else {
			//	txtResult.appendText("Studente non iscritto a questo corso");
			//	return;
			//}

			///*
			// Iscrivo lo studente al corso.
			// Controllo che l'inserimento vada a buon fine
			if (!Model.inscriviStudenteACorso(studente, corso)) {
				txtResult.appendText("Errore durante l'iscrizione al corso");
				return;
			} else {
				txtResult.appendText("Studente iscritto al corso!");
			}

		} catch (NumberFormatException e) {
			txtResult.setText("Inserire una matricola nel formato corretto.");
		} catch (RuntimeException e) {
			txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}
    	
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    	comboCorso.getSelectionModel().clearSelection();
    }

    @FXML
    void initialize() {
        assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.Model=model;
		txtResult.setStyle("-fx-font-family: monospace");
		corsi = Model.getTuttiICorsi();
		comboCorso.getItems().addAll(corsi);
	}
}
