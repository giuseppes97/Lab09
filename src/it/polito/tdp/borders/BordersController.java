/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BordersController {

	Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	void doCalcolaConfini(ActionEvent event) {
		Map<String,List<String>> mappa;
       String anno=txtAnno.getText();
       try {anno=anno.trim(); int a=Integer.parseInt(anno);
       if(a<1816 || a>2016) {txtResult.setText("Errore nell'input, inserire un anno tra 1816 e 2016!");}
       model.creaGrafo(a);
      
       } catch(Exception e) {txtResult.setText("Errore nell'input, inserire un anno tra 1816 e 2016!");}
      mappa=model.getElencoConfinanti();
      for(String s:mappa.keySet()) {
    	  txtResult.appendText("Il paese "+s+" confina con: ");
    	  for(String st:mappa.get(s)) {
    		  txtResult.appendText(st+" ");
    	  }
    	  txtResult.appendText("\n");
      }
		
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";
	}

	public void setModel(Model model2) {
		model=model2;
		
	}
}
