/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IP1New;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 *
 * @author erino
 */
public class CancelController implements Initializable {

    @FXML
    private TextField enterAccountId;

    @FXML
    private ComboBox chooseTherapyType;

    @FXML
    private Label selecttherapy;

    @FXML
    private Label enterid;

    @FXML
    private Label wrong;

    public Scanner s;

    String a;

    String b;

    boolean found;

    boolean found2;

    @FXML
    private void cancelAppoint(ActionEvent event) throws IOException {
        selecttherapy.setVisible(false);
        enterid.setVisible(false);
        wrong.setVisible(false);

        Appointment.appointid = enterAccountId.getText().trim();
        Appointment.therapist = chooseTherapyType.getSelectionModel().getSelectedItem().toString();

        if (Appointment.therapist.equals("Choose a therapy type")) {
            selecttherapy.setVisible(true);
        }

        if (Appointment.appointid.length() == 0) {
            enterid.setVisible(true);
        }

        if (!Appointment.therapist.equals("Choose a therapy type")) {

            try {
                s = new Scanner(new File("src/TherapistAppointmentData/" + Appointment.therapist + "/" + Appointment.appointid + ".txt"));

                s.useDelimiter(":");

                while (s.hasNext()) {
                    a = s.next();
                    b = s.next();

                }
                s.close();

                b = ":Not Complete,";

                PrintWriter writer = new PrintWriter("src/TherapistAppointmentData/" + Appointment.therapist + "/" + Appointment.appointid + ".txt");
                writer.print("");
                writer.print(a + b);
                writer.close();

                PrintWriter writer2 = new PrintWriter("src/AppointmentData/" + Patient.username1 + "/" + Appointment.appointid + ".txt");

                writer2.print("");
                writer2.print(a + b);
                writer2.close();

                loadpatientacc(event);

            } catch (Exception e) {
                wrong.setVisible(true);
            }
        }

    }
//Loads the patient account and closes the current fxml

    @FXML
    private void loadpatientacc(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("PatientAcc.fxml"));

        Scene scene = new Scene(root);
        Stage book = new Stage(StageStyle.DECORATED);
        book.setTitle("Book");
        book.setScene(scene);
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        book.show();
    }

    //Creates a new list for the combobox
    ObservableList list = FXCollections.observableArrayList();

    @Override

    public void initialize(URL url, ResourceBundle rb) {

        //Adds therapy types to a list then adds this list to the combobox when the cancel fxml is loaded
        list.removeAll();
        String a = "Acupuncture";
        String b = "Hairdresser";
        String c = "Spa";
        String d = "Physiotherapy";
        String e = "Sports Massage";
        list.addAll(a, b, c, d, e);
        chooseTherapyType.getItems().clear();
        chooseTherapyType.getItems().addAll(list);
        chooseTherapyType.getSelectionModel().select("Choose a therapy type");

    }

}
