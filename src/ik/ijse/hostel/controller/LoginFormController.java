package ik.ijse.hostel.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoginFormController {

    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public AnchorPane AnchorPaneContext;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
       // if(txtUserName.getText().equals("Vihanga")&&txtPassword.getText().equals("1234")) {
            setUi("DashBoardForm");
//        }else {
//            new Alert(Alert.AlertType.ERROR,"Something Wrong").show();
//        }
    }

    public void setUi(String location) throws IOException {
        Stage stage =(Stage) AnchorPaneContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
        stage.show();
    }
}
