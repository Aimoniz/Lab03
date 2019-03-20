package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {
	private Dictionary model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> ComboBox;

    @FXML
    private TextArea TxtInserimento;

    @FXML
    private Button SpellCheckButton;

    @FXML
    private TextArea TxtResult;

    @FXML
    private Label Label1;

    @FXML
    private Button ClearTextButton;

    @FXML
    private Label Label2;

    @FXML
    void ComboSelection(ActionEvent event) {
    ComboBox.setDisable(true);
    SpellCheckButton.setDisable(false);
    }

    @FXML
    void doClearText(ActionEvent event) {
TxtInserimento.clear();
TxtResult.clear();
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	TxtResult.clear();
    	double clock=System.nanoTime();
    	model.LoadDictionary(ComboBox.getValue());
    	List<String> l= new ArrayList<String>();
    	int errore=0;
    	String c[]=TxtInserimento.getText().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", " ").split(" ");
    	for(int i=0;i<c.length;i++) {
    		l.add(c[i]);}
    	List<RichWord>text=model.spellCheckText(l);
    	for(RichWord temp:text) {
    		if(temp.isCorretta()==false) {
    			TxtResult.appendText(temp.getParola()+"\n");
    			errore++;}
    		Label1.setText("the text contains"+errore+"errors");
    		ComboBox.setDisable(false);
    		double time2=System.nanoTime();
    		Label2.setText("Spell check completed in "+(time2-clock)+"second");
    		}
    	}
    	

    

    public void setModel(Dictionary model) {
		this.model = model;
		ComboBox.getItems().addAll("English","Italian");
	}

	@FXML
    void initialize() {
        assert ComboBox != null : "fx:id=\"ComboBox\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert TxtInserimento != null : "fx:id=\"TxtInserimento\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert SpellCheckButton != null : "fx:id=\"SpellCheckButton\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert TxtResult != null : "fx:id=\"TxtResult\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert Label1 != null : "fx:id=\"Label1\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert ClearTextButton != null : "fx:id=\"ClearTextButton\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert Label2 != null : "fx:id=\"Label2\" was not injected: check your FXML file 'SpellChecker.fxml'.";

    }
}
