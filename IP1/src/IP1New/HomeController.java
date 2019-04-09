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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author erino
 */
public class HomeController implements Initializable {

   

  
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

}
