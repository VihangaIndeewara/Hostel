package ik.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import ik.ijse.hostel.bo.BOFactory;
import ik.ijse.hostel.bo.SuperBO;
import ik.ijse.hostel.bo.custom.ReservationBO;
import ik.ijse.hostel.dto.ReservationDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RoomReservationDetailsFormController {
    public AnchorPane AnchorPaneContext;
    public TableView<ReservationDTO> tblRoomsReservationDetails;
    public TableColumn colStudentId;
    public TableColumn colRoomTypeId;
    public TableColumn colType;
    public TableColumn colKeyMoney;
    public TableColumn colPaidAmount;
    public TableColumn colDateFrom;
    public TableColumn colDateTo;
    public TableColumn colReservationId;
    public JFXTextField txtReservationId;
    public JFXTextField txtPaidAmount;
    public JFXButton btnUpdate;

    ReservationBO reservationBO = (ReservationBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RESERVATION);

    public void initialize() throws IOException {
        colReservationId.setCellValueFactory(new PropertyValueFactory<>("resId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("sId"));
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("rId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colPaidAmount.setCellValueFactory(new PropertyValueFactory<>("payingAmount"));
        colDateFrom.setCellValueFactory(new PropertyValueFactory<>("dateFrom"));
        colDateTo.setCellValueFactory(new PropertyValueFactory<>("dateTo"));

        setReservationDetails();

        tblRoomsReservationDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData(newValue);
        });

        btnUpdate.setDisable(true);
    }

    private void setData(ReservationDTO newValue) {
        if (newValue!=null) {
            txtReservationId.setText(newValue.getResId());
            txtPaidAmount.setText(String.valueOf(newValue.getPayingAmount()));
        }
        btnUpdate.setDisable(false);
    }

    public void setReservationDetails() throws IOException {
        ObservableList<ReservationDTO> allList = reservationBO.getAllReservationDetails();
        tblRoomsReservationDetails.setItems(allList);
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
         if (reservationBO.updatePayingAmount(Double.parseDouble(txtPaidAmount.getText()),txtReservationId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION,"Updated!!!").show();
            clear();
            setReservationDetails();
        }
    }

    public void clear(){
        txtReservationId.clear();
        txtPaidAmount.clear();

        btnUpdate.setDisable(true);
    }
}
