package ik.ijse.hostel.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class DashBoardFormController {
    public AnchorPane AllDashBoardContent;
    public AnchorPane AnchorPaneContext;
    public Label lblTime;
    public Label lblDate;

    public void initialize() throws IOException {
       setDashBoard();
       loadDateAndTime();
    }

    private void loadDateAndTime() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }


    private void setDashBoard() throws IOException {
        setUi("StudentForm");
    }

    public void btnAddStudentOnAction(ActionEvent actionEvent) throws IOException {
        setDashBoard();
    }

    public void btnAddRoomOnAction(ActionEvent actionEvent) throws IOException {
        setUi("RoomForm");
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        setLogOutUi("LoginForm");
    }

    public void btnReserveRoomOnAction(ActionEvent actionEvent) throws IOException {
        setUi("RoomReservationForm");
    }

    public void btnReservationDetailsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("RoomReservationDetailsForm");
    }

    private void setUi(String location) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"));
        AnchorPaneContext.getChildren().add(parent);
    }


    private void setLogOutUi(String location) throws IOException {
        Stage stage= (Stage) AllDashBoardContent.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
        stage.show();
    }
}
