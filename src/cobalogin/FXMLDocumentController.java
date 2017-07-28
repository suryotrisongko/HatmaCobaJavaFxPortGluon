/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobalogin;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author hatma
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField txt1;

    
    
    EntityManagerFactory emf;
    TabelhatmaJpaController jpa;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        Tabelhatma x = new Tabelhatma("hatma");
        try {
            jpa.create(x);
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        emf = Persistence.createEntityManagerFactory("CobaLoginPU");
        jpa = new TabelhatmaJpaController(emf);
    }

}
