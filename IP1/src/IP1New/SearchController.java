/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IP1New;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author stani
 */
public class SearchController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField searchID;

    @FXML
    private Label cantFindID;

    @FXML
    private void displayPatientInf(ActionEvent event) throws IOException {
        cantFindID.setVisible(false);
        Patient.username1 = searchID.getText();

        boolean isTrue = Patient.verifySearch(Patient.username1.trim());

        if (isTrue == true) {
            Parent root = FXMLLoader.load(getClass().getResource("PatientInf.fxml"));

            Scene scene = new Scene(root);
            Stage inf = new Stage(StageStyle.DECORATED);
            inf.setTitle("Patient Information");
            inf.setScene(scene);
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
            inf.show();

        } else {
            cantFindID.setVisible(true);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
