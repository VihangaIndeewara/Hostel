package ik.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import ik.ijse.hostel.bo.BOFactory;
import ik.ijse.hostel.bo.custom.RoomBO;
import ik.ijse.hostel.bo.custom.impl.RoomBOImpl;
import ik.ijse.hostel.dto.RoomDTO;
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

public class RoomFormController {
    public AnchorPane AnchorPaneContext;
    public TableView<RoomDTO> tblRoom;
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
    public JFXButton btnDelete;
    public JFXButton btnUpdate;


    RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
    LinkedHashMap<JFXTextField, Pattern> hashMap =new LinkedHashMap<>();


    public void initialize() throws IOException {
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colRoomQty.setCellValueFactory(new PropertyValueFactory<>("qty"));


        Pattern idPattern=Pattern.compile("^RM-[0-9]{4}$");
        Pattern typeNamePattern = Pattern.compile("^[A-z /-]{2,20}$");
        Pattern keyMoneyPattern = Pattern.compile("^[1-9]{1}[0-9]*(.00)?$");
        Pattern qtyPattern= Pattern.compile("^[1-9]{1}[0-9]*$");

        hashMap.put(txtRoomTypeId,idPattern);
        hashMap.put(txtType,typeNamePattern);
        hashMap.put(txtKeyMoney,keyMoneyPattern);
        hashMap.put(txtRoomQty,qtyPattern);

        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData(newValue);
        });

        loadTableData();
        btnSave.setDisable(true);


    }

    private void setData(RoomDTO newValue) {
        if (newValue!=null) {
            txtUpdateRoomTypeId.setText(newValue.getRoomTypeId());
            txtUpdateType.setText(newValue.getType());
            txtUpdateKeyMoney.setText(String.valueOf(newValue.getKeyMoney()));
            txtUpdateRoomQty.setText(String.valueOf(newValue.getQty()));
        }
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
    }

    private void loadTableData() throws IOException {
        tblRoom.setItems(roomBO.getAllRoom());
    }



    public void txtKeyReleased(KeyEvent keyEvent) throws IOException {
        validation();

        if (keyEvent.getCode()== KeyCode.ENTER){
            Object response = validation();

            if (response instanceof JFXTextField){
                JFXTextField textField= (JFXTextField) response;
                textField.requestFocus();
            }else if (response instanceof Boolean){
                saveRoom();
            }
        }
    }

    public Object validation(){
        for (JFXTextField textField:hashMap.keySet()) {
            Pattern pattern = hashMap.get(textField);

            if (pattern.matcher(textField.getText()).matches()){
                accept(textField);
            }else {
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
        btnSave.setDisable(true);
    }

    private void accept(JFXTextField textField) {
        textField.setFocusColor(Paint.valueOf("#08b506"));
        btnSave.setDisable(false);
    }


    public void saveRoom() throws IOException {
        RoomDTO dto1 = new RoomDTO(txtRoomTypeId.getText(), txtType.getText(), Double.parseDouble(txtKeyMoney.getText()), Integer.parseInt(txtRoomQty.getText()));
        if (roomBO.saveRoom(dto1)) {
            new Alert(Alert.AlertType.CONFIRMATION,"Saved!!!").show();
        }


        loadTableData();
        clearTexts();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws IOException {
        saveRoom();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        if (roomBO.updateRoom(new RoomDTO(txtUpdateRoomTypeId.getText(),txtUpdateType.getText(),Double.parseDouble(txtUpdateKeyMoney.getText()),Integer.parseInt(txtUpdateRoomQty.getText())))) {
            new Alert(Alert.AlertType.CONFIRMATION,"Updated!!!").show();
        }
        loadTableData();
        clearUpdateTexts();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws IOException {
        if (roomBO.deleteRoom(txtUpdateRoomTypeId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted!!!").show();
        }
        loadTableData();
        clearUpdateTexts();
    }

    public void clearTexts(){
        txtRoomTypeId.clear();
        txtType.clear();
        txtRoomQty.clear();
        txtKeyMoney.clear();

        txtRoomTypeId.requestFocus();
        btnSave.setDisable(true);

        txtRoomTypeId.setFocusColor(Paint.valueOf("#4059a9"));
        txtType.setFocusColor(Paint.valueOf("#4059a9"));
        txtRoomQty.setFocusColor(Paint.valueOf("#4059a9"));
        txtKeyMoney.setFocusColor(Paint.valueOf("#4059a9"));
    }

    public void clearUpdateTexts(){
        txtUpdateRoomTypeId.clear();
        txtUpdateType.clear();
        txtUpdateKeyMoney.clear();
        txtUpdateRoomQty.clear();

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

}
