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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author stani
 */
public class PatientInfController implements Initializable {

    @FXML
    private TextField InfoName;
    @FXML
    private TextField InfoId;
    @FXML
    private TextField InfoDob;

    @FXML
    private void finishAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TherapistAcc.fxml"));

        Scene scene = new Scene(root);
        Stage back = new Stage(StageStyle.DECORATED);
        back.setTitle("Finish Acttion");
        back.setScene(scene);
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        back.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InfoDob.setText(Patient.dob1);
        InfoName.setText(Patient.name1 + " " + Patient.name2);
        InfoId.setText(Patient.username1);
    }

}
