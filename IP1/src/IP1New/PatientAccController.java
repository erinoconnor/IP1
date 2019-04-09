/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IP1New;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author erino
 */
public class PatientAccController implements Initializable {

    @FXML
    private TextField AccountUsername;
    @FXML
    private TextField AccountName;
    @FXML
    private TextField AccountDob;
    @FXML
    private TextArea appointments;

    @FXML
    private void deleteAppointment(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("cancel.fxml"));

        Scene scene = new Scene(root);
        Stage cancel = new Stage(StageStyle.DECORATED);
        cancel.setTitle("Cancel Appointment");
        cancel.setScene(scene);
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        cancel.show();
    }

    //Logs out of the patients account and closes it. The login page is then displayed
    @FXML
    private void logOffAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene scene = new Scene(root);
        Stage logoff = new Stage(StageStyle.DECORATED);
        logoff.setTitle("Log Off");
        logoff.setScene(scene);
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

        logoff.show();

    }

//Opens the book appointment page and closes the patient account page so that the user can book an appointmnet
    @FXML
    private void newAppointment(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("book.fxml"));

        Scene scene = new Scene(root);
        Stage book = new Stage(StageStyle.DECORATED);
        book.setTitle("Book");
        book.setScene(scene);
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        book.show();

    }
    public String line;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Sets the text of the Text Fields on the Patient Accounts to the users info
        AccountUsername.setText(Patient.username1);
        AccountName.setText(Patient.name1 + " " + Patient.name2);
        AccountDob.setText(Patient.dob1);

        /*The readappointments method is called and reads the appointment list into a new list called newlist 
        which is then converted to an array and displays each appointment into a TextArea
         */
        String target_dir = "src/AppointmentData/" + Patient.username1;
        File dir = new File(target_dir);
        File[] files = dir.listFiles();
        try {
            for (File f : files) {
                if (f.isFile()) {
                    BufferedReader inputStream = null;

                    try {
                        inputStream = new BufferedReader(
                                new FileReader(f));

                        while ((line = inputStream.readLine()) != null) {
                            appointments.appendText(line);
                            appointments.appendText("\n");

                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(PatientAccController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(PatientAccController.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        if (inputStream != null) {

                            try {
                                inputStream.close();
                            } catch (IOException ex) {
                                Logger.getLogger(PatientAccController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }

                    }
                }
            }

        } catch (Exception e) {
            System.out.println("No appointments");

        }
    }
}
