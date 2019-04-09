/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IP1New;

import java.io.File;
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
public class AppointSearchController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField appointmentid;

    @FXML
    private Label cantFindId;

    @FXML
    private void searchappoint(ActionEvent event) throws IOException {
        Appointment.appointid = appointmentid.getText().trim();
        cantFindId.setVisible(false);
        File file2 = new File("src/TherapistAppointmentData/" + Therapist.therapyType + "/" + Appointment.appointid + ".txt");

        if (file2.exists()) {
            Parent root = FXMLLoader.load(getClass().getResource("appointdetails.fxml"));

            Scene scene = new Scene(root);
            Stage details = new Stage(StageStyle.DECORATED);
            details.setTitle("AppointmentDetails");
            details.setScene(scene);
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
            details.show();

        } else {
            cantFindId.setVisible(true);
        }

    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TherapistAcc.fxml"));

        Scene scene = new Scene(root);
        Stage theracc = new Stage(StageStyle.DECORATED);
        theracc.setTitle("AppointmentDetails");
        theracc.setScene(scene);
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        theracc.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
