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
 * @author stani
 */
public class TherapistAccController implements Initializable {

    @FXML
    private TextField AccountId;
    @FXML
    private TextField AccountName;
    @FXML
    private TextField AccountTherapy;
    @FXML
    private TextArea appointmentsArea;

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

    @FXML
    private void AppointmentSearch(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Therapistappointsearch.fxml"));

        Scene scene = new Scene(root);
        Stage search = new Stage(StageStyle.DECORATED);
        search.setTitle("Search");
        search.setScene(scene);
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        search.show();
    }

    @FXML
    private void PatientSearch(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Search.fxml"));

        Scene scene = new Scene(root);
        Stage search = new Stage(StageStyle.DECORATED);
        search.setTitle("Search");
        search.setScene(scene);
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        search.show();
    }

    public String line;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AccountId.setText(Therapist.username);
        AccountName.setText(Therapist.name1 + " " + Therapist.name2);
        AccountTherapy.setText(Therapist.therapyType);

        String target_dir = "src/TherapistAppointmentData/" + Therapist.therapyType;
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
                            appointmentsArea.appendText(line);
                            appointmentsArea.appendText("\n");

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
