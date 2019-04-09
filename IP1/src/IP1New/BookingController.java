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
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author erino
 */
public class BookingController implements Initializable {

    LocalDate date = LocalDate.now();

    @FXML
    private DatePicker appointdate;

    @FXML
    private Label typeValid;

    @FXML
    private Label timeValid;

    @FXML
    private Label datepastValid;

    @FXML
    private Label dateemptyValid;

    @FXML
    private RadioButton morning;

    @FXML
    private RadioButton afternoon;

    @FXML
    private ComboBox therapisttype;

    private double[] morningTimes = new double[]{7.00, 7.30, 8.00, 8.30, 9.00, 9.30, 10.00, 10.30, 11.00, 11.30, 12.00};

    private Random rand = new Random();

    @FXML
    private void bookappointment(ActionEvent event) throws IOException {

        typeValid.setVisible(false);
        dateemptyValid.setVisible(false);
        datepastValid.setVisible(false);
        timeValid.setVisible(false);

        LocalDate appointmentdate = appointdate.getValue();

        Appointment.therapist = therapisttype.getSelectionModel().getSelectedItem().toString();

        if (Appointment.therapist.equals("Choose a therapy type")) {
            typeValid.setVisible(true);
        }

        if (morning.isSelected()) {
            float[] morningTimes = new float[]{7.0f, 7.3f, 8.0f, 8.3f, 9.0f, 9.3f, 10.0f, 10.3f, 11.0f, 11.3f};
            morning.getText();
            float time = morningTimes[rand.nextInt(morningTimes.length)];
            Appointment.time = (String.format("%.02f", time) + "am");

        }
        if (afternoon.isSelected()) {
            float[] afternoonTimes = new float[]{12.0f, 12.3f, 1.0f, 1.3f, 2.0f, 2.3f, 3.0f, 3.3f, 4.0f, 4.3f, 5.0f};
            afternoon.getText();
            float time = afternoonTimes[rand.nextInt(afternoonTimes.length)];
            Appointment.time = (String.format("%.02f", time) + "pm");

        }

        if (appointmentdate == null) {
            dateemptyValid.setVisible(true);

        } else if (appointmentdate.compareTo(date) < 0) {
            datepastValid.setVisible(true);

        }

        if (!afternoon.isSelected() && !morning.isSelected()) {
            timeValid.setVisible(true);
        }

        if (appointmentdate != null && appointmentdate.compareTo(date) > 0 && therapisttype != null && !Appointment.therapist.equals("Choose a therapy type")) {
            if (afternoon.isSelected() | morning.isSelected()) {
                Appointment.writeappointments(appointmentdate);

                cancelBooking(event);
            }
        }

    }

    @FXML
    private void cancelBooking(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PatientAcc.fxml"));

        Scene scene = new Scene(root);
        Stage cancel = new Stage(StageStyle.DECORATED);
        cancel.setTitle("Cancel booking");
        cancel.setScene(scene);
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        cancel.show();

    }

    //Creates a new list for the combobox
    ObservableList list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Removes all contents of list and adds 
        list.removeAll();
        String a = "Acupuncture";
        String b = "Hairdresser";
        String c = "Spa";
        String d = "Physiotherapy";
        String e = "Sports Massage";
        list.addAll(a, b, c, d, e);
        therapisttype.getItems().clear();
        therapisttype.getItems().addAll(list);
        therapisttype.getSelectionModel().select("Choose a therapy type");

    }

}
