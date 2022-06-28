package ik.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class RoomReservationFormController {
    public AnchorPane AnchorPaneContext;
    public TableView tblReserveRoom;
    public TableColumn colStudentId;
    public TableColumn colRoomTypeId;
    public TableColumn colType;
    public TableColumn colKeyMoney;
    public TableColumn colPayingAmount;
    public TableColumn colFromDate;
    public TableColumn colToDate;
    public JFXTextField txtStudentName;
    public JFXTextField txtType;
    public JFXTextField txtKeyMoney;
    public JFXButton btnAdd;
    public JFXComboBox cmbStudentId;
    public JFXTextField txtRegistrationNo;
    public JFXComboBox cmbRoomTypeId;
    public JFXTextField txtRoomsAvailability;
    public JFXTextField txtPayingAmount;
    public JFXButton btnRegister;
    public JFXTextField txtFromDate;
    public JFXTextField txtToDate;

    public void btnRegisterOnAction(ActionEvent actionEvent) {
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
    }

    public void txtKeyReleased(KeyEvent keyEvent) {
    }
}
