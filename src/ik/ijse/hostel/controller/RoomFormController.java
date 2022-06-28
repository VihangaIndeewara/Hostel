package ik.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class RoomFormController {
    public AnchorPane AnchorPaneContext;
    public TableView tblRoom;
    public TableColumn colRoomTypeId;
    public TableColumn colType;
    public TableColumn colKeyMoney;
    public TableColumn colRoomQty;
    public JFXTextField txtRoomTypeId;
    public JFXTextField txtType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtRoomQty;
    public JFXButton btnSave;
    public JFXTextField txtUpdateRoomTypeId;
    public JFXTextField txtUpdateType;
    public JFXTextField txtUpdateKeyMoney;
    public JFXTextField txtUpdateRoomQty;

    public void txtKeyReleased(KeyEvent keyEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }
}
