/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IP1New;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author stani
 */
public class AppointDetailsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextArea appointDetails;

    @FXML
    private TextField userID;

    @FXML
    private ComboBox status;

    @FXML
    private Label enterID;

    @FXML
    private Label chooseStatus;

    @FXML
    private Label wrongID;

    public Scanner s;

    String a;
    String b;

    //Updates the status of the appointment
    @FXML
    private void update(ActionEvent event) throws IOException {
        enterID.setVisible(false);
        chooseStatus.setVisible(false);
        wrongID.setVisible(false);

        Patient.username1 = userID.getText().trim();

        String statusSelection = status.getSelectionModel().getSelectedItem().toString();
//If no userid is entered an error is displayed
        if (Patient.username1.length() == 0) {
            enterID.setVisible(true);
        }
//If no status is chosen an error is displayed
        if (statusSelection.equals("Choose status")) {
            chooseStatus.setVisible(true);
        }

        try {
//Reads in the line before colon and line after colon. The line after the colon is the current status and is read in the variable b.
            if (!statusSelection.equals("Choose status") && Patient.username1.length() != 0) {

                if (Patient.username1.length() != 0 & !statusSelection.equals("Choose status")) {
                    s = new Scanner(new File("src/TherapistAppointmentData/" + Therapist.therapyType + "/" + Appointment.appointid + ".txt"));

                    s.useDelimiter(":");

                    while (s.hasNext()) {
                        a = s.next();
                        b = s.next();

                    }
                    s.close();

                }

//If the therapist updates the status , the variable b is set to that status
//The two files in the therapists and users appointment info are then cleared
//The first half of the appointment details, which are stored in variable a, is written to both files and then the updated variable b.
                if (statusSelection.equals("In progress")) {

                    b = ":In progress,";

                    PrintWriter writer = new PrintWriter("src/TherapistAppointmentData/" + Therapist.therapyType + "/" + Appointment.appointid + ".txt");
                    writer.print("");
                    writer.print(a + b);
                    writer.close();

                    PrintWriter writer2 = new PrintWriter("src/AppointmentData/" + Patient.username1 + "/" + Appointment.appointid + ".txt");

                    writer2.print("");
                    writer2.print(a + b);
                    writer2.close();

                    cancelButton(event);
                } else if (statusSelection.equals("Pending")) {
                    b = ":Pending,";

                    PrintWriter writer = new PrintWriter("src/TherapistAppointmentData/" + Therapist.therapyType + "/" + Appointment.appointid + ".txt");
                    writer.print("");
                    writer.print(a + b);
                    writer.close();

                    PrintWriter writer2 = new PrintWriter("src/AppointmentData/" + Patient.username1 + "/" + Appointment.appointid + ".txt");

                    writer2.print("");
                    writer2.print(a + b);
                    writer2.close();

                    cancelButton(event);
                } else if (statusSelection.equals("Not Complete")) {
                    b = ":Not Complete,";

                    PrintWriter writer = new PrintWriter("src/TherapistAppointmentData/" + Therapist.therapyType + "/" + Appointment.appointid + ".txt");
                    writer.print("");
                    writer.print(a + b);
                    writer.close();

                    PrintWriter writer2 = new PrintWriter("src/AppointmentData/" + Patient.username1 + "/" + Appointment.appointid + ".txt");

                    writer2.print("");
                    writer2.print(a + b);
                    writer2.close();

                    cancelButton(event);
                } else if (statusSelection.equals("Complete")) {
                    b = ":Complete,";

                    PrintWriter writer = new PrintWriter("src/TherapistAppointmentData/" + Therapist.therapyType + "/" + Appointment.appointid + ".txt");
                    writer.print("");
                    writer.print(a + b);
                    writer.close();

                    PrintWriter writer2 = new PrintWriter("src/AppointmentData/" + Patient.username1 + "/" + Appointment.appointid + ".txt");

                    writer2.print("");
                    writer2.print(a + b);
                    writer2.close();

                    cancelButton(event);

                }
            }
        } catch (Exception e) {
            wrongID.setVisible(true);
        }

    }

//Loads the therapist account
    @FXML
    private void cancelButton(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("TherapistAcc.fxml"));

        Scene scene = new Scene(root);
        Stage theracc = new Stage(StageStyle.DECORATED);
        theracc.setTitle("Book");
        theracc.setScene(scene);
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        theracc.show();
    }

    ObservableList list = FXCollections.observableArrayList();
    public Scanner x;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            x = new Scanner(new File("src/TherapistAppointmentData/" + Therapist.therapyType + "/" + Appointment.appointid + ".txt"));

            String appointment1 = "";
            x.useDelimiter(",");

            List<String> list = new ArrayList<String>();
            while (x.hasNext()) {
                appointment1 = x.next();
                list.add(appointment1);

            }
            x.close();
            String[] arr = list.toArray(new String[list.size()]);
            appointDetails.appendText(arr[0]);
        } catch (FileNotFoundException e) {
            appointDetails.appendText("Can't find appointment");

        }
        //Adds the status options to the combobox when the appointment details page is loaded
        list.removeAll();
        String a = "Pending";
        String b = "In progress";
        String c = "Not Complete";
        String d = "Complete";

        list.addAll(a, b, c, d);
        status.getItems().clear();
        status.getItems().addAll(list);

        status.getSelectionModel().select("Choose status");
    }

}
