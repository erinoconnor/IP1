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
 *
 * @author erino
 */
public class LoginController implements Initializable {

    @FXML
    private Label cantlogin1;

    @FXML
    private Label cantlogin2;

    @FXML
    private TextField userLoginField;

    @FXML
    private TextField userpasswordField;

    @FXML
    private TextField loginId;

    @FXML
    private TextField loginPsswd;

    @FXML
    private void patientLogin(ActionEvent event) throws IOException {
        cantlogin1.setVisible(false);

        Patient.username1 = userLoginField.getText();
        Patient.pass1 = userpasswordField.getText();

        boolean isTrue = Patient.verifyLogin(Patient.username1, Patient.pass1);

        if (isTrue == true) {

            Parent root = FXMLLoader.load(getClass().getResource("PatientAcc.fxml"));

            Scene scene = new Scene(root);
            Stage patient = new Stage(StageStyle.DECORATED);
            patient.setTitle("Patient Account");
            patient.setScene(scene);
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
            patient.show();

        } else {
            cantlogin1.setVisible(true);
        }

    }

    @FXML
    private void therapistLogin(ActionEvent event) throws IOException {
        cantlogin2.setVisible(false);

        Therapist.username = loginId.getText();
        Therapist.pass = loginPsswd.getText();
        boolean isTrue = Therapist.verifyLoginT(Therapist.username, Therapist.pass);

        if (isTrue == true) {
            Parent root = FXMLLoader.load(getClass().getResource("TherapistAcc.fxml"));

            Scene scene = new Scene(root);
            Stage therapist = new Stage(StageStyle.DECORATED);
            therapist.setTitle("Therapist Account");
            therapist.setScene(scene);
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
            therapist.show();

        } else {
            cantlogin2.setVisible(true);
        }

    }


    /* Displays the register window and closes current open window */
    @FXML
    private void showregButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));

        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("Register");
        reg.setScene(scene);
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        reg.show();

    }

//  Displays home screen and closes current open window
    @FXML
    public void showhomeButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));

        Scene scene = new Scene(root);
        Stage home = new Stage(StageStyle.DECORATED);
        home.setTitle("Home");
        home.setScene(scene);
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        home.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

}
