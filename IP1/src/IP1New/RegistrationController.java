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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author erino
 */
public class RegistrationController implements Initializable {

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmpassword;

    @FXML
    private TextField forename1;

    @FXML
    private TextField surname2;

    @FXML
    private DatePicker dateofbirth;

    @FXML
    private Label enterforename;

    @FXML
    private Label entersurname;

    @FXML
    private Label passwordmatch;

    @FXML
    private Label enterpassword;

    @FXML
    private Label futuredate;

    @FXML
    private Label selectdob;

    @FXML
    private Label charactercount;

    @FXML
    private Label usernameshow;

    @FXML
    private Button registerButton;
    @FXML
    private Label invalidforename;

    @FXML
    private Label invalidsurname;

    @FXML
//   Action performed when register button is clicked on home screen
    private void regButtonAction(ActionEvent event) throws IOException {

//    Reading in the data from the user
        String forename = forename1.getText();
        String surname = surname2.getText();
        String psswd = password.getText();
        String confirm = confirmpassword.getText();
        LocalDate dob = dateofbirth.getValue();

//    Setting the validation labels to invisible
        enterforename.setVisible(false);
        entersurname.setVisible(false);
        passwordmatch.setVisible(false);
        enterpassword.setVisible(false);
        selectdob.setVisible(false);
        charactercount.setVisible(false);
        futuredate.setVisible(false);
        invalidforename.setVisible(false);
        invalidsurname.setVisible(false);

//        Validation for the register screen
        boolean match = psswd.equals(confirm);
        LocalDate date = LocalDate.now();

        if (match == false) {
            passwordmatch.setVisible(true);
        }

        if (psswd.length() > 12 || psswd.length() < 8) {
            charactercount.setVisible(true);

        }
        if (psswd.length() == 0) {
            enterpassword.setVisible(true);
        }

        if (forename.length() == 0) {
            enterforename.setVisible(true);
        }
        if (surname.length() == 0) {
            entersurname.setVisible(true);
        }
        if (dob == null) {
            selectdob.setVisible(true);

        }
        else if (dob.compareTo(date) > 0) {
            futuredate.setVisible(true);
        }
        
       if (!forename.matches("[a-zA-Z ,]+")) {
            invalidforename.setVisible(true);
        }
        if (!surname.matches("[a-zA-Z ,]+")) {
            invalidsurname.setVisible(true);
        }
 
        

        //        Validating all conditions before a username is created
        if (forename.length() != 0 && surname.length() != 0 && psswd.length() != 0 && dob != null && match == true && dob.compareTo(date) < 0 && forename.matches("[a-zA-Z ,]+") && surname.matches("[a-zA-Z ,]+")) {
            if (psswd.length() > 7 && psswd.length() < 13) {
                String userid = Patient.makeusername(forename, surname);

                usernameshow.setText(userid);
                Patient.write(userid, psswd, forename, surname, dob);
                registerButton.setDisable(true);

            }
        }
    }

    @FXML
    private void loginButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene scene = new Scene(root);
        Stage login = new Stage(StageStyle.DECORATED);
        login.setTitle("Login");
        login.setScene(scene);
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        login.show();
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
