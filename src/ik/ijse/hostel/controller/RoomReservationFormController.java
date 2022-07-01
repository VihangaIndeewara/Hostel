package ik.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import ik.ijse.hostel.bo.custom.ReservationBO;
import ik.ijse.hostel.bo.custom.RoomBO;
import ik.ijse.hostel.bo.custom.StudentBO;
import ik.ijse.hostel.bo.custom.impl.ReservationBOImpl;
import ik.ijse.hostel.bo.custom.impl.RoomBOImpl;
import ik.ijse.hostel.bo.custom.impl.StudentBOImpl;
import ik.ijse.hostel.dto.ReservationDTO;
import ik.ijse.hostel.dto.RoomDTO;
import ik.ijse.hostel.entity.Reservation;
import ik.ijse.hostel.entity.Room;
import ik.ijse.hostel.entity.Student;
import ik.ijse.hostel.view.TM.CartTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class RoomReservationFormController {
    public AnchorPane AnchorPaneContext;
    public TableView<CartTM> tblReserveRoom;
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
    public JFXComboBox<String> cmbStudentId;
    public JFXTextField txtReservationNo;
    public JFXComboBox<String> cmbRoomTypeId;
    public JFXTextField txtRoomsAvailability;
    public JFXTextField txtPayingAmount;
    public JFXButton btnRegister;
    public JFXTextField txtFromDate;
    public JFXTextField txtToDate;

    ReservationBO reservationBO = new ReservationBOImpl();
    StudentBO studentBO = new StudentBOImpl();
    RoomBO roomBO = new RoomBOImpl();

    LinkedHashMap<JFXTextField, Pattern> hashMap=new LinkedHashMap<>();

    String reservationNo="";

    public void initialize() throws IOException {
        setReservationNo();
        getStudentsId();
        getRoomTypesIds();
        btnAdd.setDisable(true);

        colStudentId.setCellValueFactory(new PropertyValueFactory<>("stuId"));
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colPayingAmount.setCellValueFactory(new PropertyValueFactory<>("payingAmount"));
        colFromDate.setCellValueFactory(new PropertyValueFactory<>("dateFrom"));
        colToDate.setCellValueFactory(new PropertyValueFactory<>("dateTo"));

        Pattern amountPattern=Pattern.compile("^[1-9]{1}[0-9]*(.00)?$");
        Pattern fromDatePattern=Pattern.compile("[0-9]{4}[-][0-9]{2}[-][0-9]{2}$");
        Pattern toDatePattern=Pattern.compile("[0-9]{4}[-][0-9]{2}[-][0-9]{2}$");

        hashMap.put(txtPayingAmount,amountPattern);
        hashMap.put(txtFromDate,fromDatePattern);
        hashMap.put(txtToDate,toDatePattern);

        cmbStudentId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setStudentData(newValue);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        cmbRoomTypeId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setRoomData(newValue);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setRoomData(String id) throws IOException {
        ObservableList<RoomDTO> roomsData = roomBO.getRoomsData(id);

        for (RoomDTO r : roomsData) {
            txtType.setText(r.getType());
            txtRoomsAvailability.setText(String.valueOf(r.getQty()));
            txtKeyMoney.setText(String.valueOf(r.getKeyMoney()));
        }

    }

    private void getRoomTypesIds() throws IOException {
        cmbRoomTypeId.setItems(roomBO.getAllRoomTypeIds());
    }

    private void setStudentData(String id) throws IOException {
        txtStudentName.setText(studentBO.getStudentName(id));
    }


    private void getStudentsId() throws IOException {
        ObservableList<String> allStudentIds = studentBO.getAllStudentIds();
        cmbStudentId.setItems(allStudentIds);
    }

    private void setReservationNo() throws IOException {
        txtReservationNo.setText(reservationBO.getNewId());
    }


    public void txtKeyReleased(KeyEvent keyEvent) {
        validation();

        if (keyEvent.getCode()== KeyCode.ENTER){
            Object response = validation();

            if (response instanceof JFXTextField){
                JFXTextField t = (JFXTextField) response;
                t.requestFocus();
            }else if (response instanceof  Boolean){
                add();
            }
        }

    }

    public Object validation(){
        for (JFXTextField textField:hashMap.keySet()){
            Pattern pattern = hashMap.get(textField);

            if (pattern.matcher(textField.getText()).matches()){
                accept(textField);
            }else{
                error(textField);
                return textField;
            }
        }
        return true;
    }

    private void error(JFXTextField textField) {
        if (textField.getText().length()>0) {
            textField.setFocusColor(Paint.valueOf("red"));
        }
        btnAdd.setDisable(true);
    }

    private void accept(JFXTextField textField) {
        textField.setFocusColor(Paint.valueOf("#08b506"));
        btnAdd.setDisable(false);
    }


    ObservableList<CartTM> tm= FXCollections.observableArrayList();
    private void add() {
        tm.add(new CartTM(
                cmbStudentId.getValue(),
                cmbRoomTypeId.getValue(),
                txtType.getText(),
                Double.parseDouble(txtKeyMoney.getText()),
                Double.parseDouble(txtPayingAmount.getText()),
                txtFromDate.getText(),
                txtToDate.getText()
        ));

        tblReserveRoom.setItems(tm);
        reservationNo=txtReservationNo.getText();
        btnAdd.setDisable(true);
        btnRegister.setDisable(false);

    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        add();
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) throws IOException {
        Student student = new Student();
        student.setStudentId(cmbStudentId.getValue());

        Room room = new Room();
        room.setRoomTypeId(cmbRoomTypeId.getValue());

        ReservationDTO dto = new ReservationDTO(
                reservationNo,
                txtType.getText(),
                Double.parseDouble(txtKeyMoney.getText()),
                Double.parseDouble(txtPayingAmount.getText()),
                txtFromDate.getText(),
                txtToDate.getText(),
                student,
                room
        );

        if (reservationBO.registerNewReservation(dto)) {
            new Alert(Alert.AlertType.CONFIRMATION,"Reservation Successful").show();

            if (updateRoomQty(cmbRoomTypeId.getValue())){
                clear();
                setReservationNo();
                tm.clear();
            }
        }

    }

    public boolean updateRoomQty(String id)throws IOException{
       return roomBO.updateRoomQty(id);

    }



    public void clear(){
        cmbStudentId.getSelectionModel().clearSelection();
        txtStudentName.clear();
        cmbRoomTypeId.getSelectionModel().clearSelection();
        txtType.clear();
        txtRoomsAvailability.clear();
        txtKeyMoney.clear();
        txtPayingAmount.clear();
        txtFromDate.clear();
        txtToDate.clear();

        btnAdd.setDisable(true);
        btnRegister.setDisable(true);

        cmbStudentId.requestFocus();
        txtPayingAmount.setFocusColor(Paint.valueOf("#4059a9"));
        txtFromDate.setFocusColor(Paint.valueOf("#4059a9"));
        txtToDate.setFocusColor(Paint.valueOf("#4059a9"));
    }

}
