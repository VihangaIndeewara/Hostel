package ik.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import ik.ijse.hostel.bo.BOFactory;
import ik.ijse.hostel.bo.custom.RoomBO;
import ik.ijse.hostel.bo.custom.StudentBO;
import ik.ijse.hostel.bo.custom.impl.StudentBOImpl;
import ik.ijse.hostel.dto.StudentDTO;
import ik.ijse.hostel.entity.Student;
import ik.ijse.hostel.util.FactoryConfiguration;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import org.hibernate.Session;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class StudentFormController {
    public AnchorPane AnchorPaneContext;
    public TableView<StudentDTO> tblStudent;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colDOB;
    public TableColumn colGender;
    public TableColumn colContactNo;
    public JFXTextField txtStudentId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtDOB;
    public JFXTextField txtContactNo;
    public JFXButton btnSave;
    public JFXTextField txtUpdateStudentId;
    public JFXTextField txtUpdateName;
    public JFXTextField txtUpdateDOB;
    public JFXTextField txtUpdateGender;
    public JFXTextField txtUpdateContactNo;
    public JFXTextField txtUpdateAddress;
    public JFXTextField txtGender;
    public JFXButton btnDelete;
    public JFXButton btnUpdate;


    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);
    LinkedHashMap<JFXTextField, Pattern> hashMap=new LinkedHashMap<>();

    public void initialize() throws IOException {
        generateId();
        loadAllData();

        Pattern namePattern =Pattern.compile("^[A-z ]{3,20}$");
        Pattern addressPattern= Pattern.compile("^[A-z0-9 / , ]{4,50}$");
        Pattern dobPattern = Pattern.compile("^[0-9]{4}[-][0-9]{2}[-][0-9]{2}$");
        Pattern genderPattern = Pattern.compile("[A-z ]{3,8}$");
        Pattern contactNoPattern = Pattern.compile("^[+940-9]{12}$");

        hashMap.put(txtName,namePattern);
        hashMap.put(txtAddress,addressPattern);
        hashMap.put(txtDOB,dobPattern);
        hashMap.put(txtGender,genderPattern);
        hashMap.put(txtContactNo,contactNoPattern);


        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        
        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData(newValue);
        });

        btnSave.setDisable(true);
    }

    private void setData(StudentDTO newValue) {
        if (newValue!=null){
            txtUpdateStudentId.setText(newValue.getStudentId());
            txtUpdateName.setText(newValue.getName());
            txtUpdateAddress.setText(newValue.getAddress());
            txtUpdateDOB.setText(newValue.getDob());
            txtUpdateGender.setText(newValue.getGender());
            txtUpdateContactNo.setText(newValue.getContactNo());
        }
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
    }

    private void generateId() throws IOException {
        txtStudentId.setText(studentBO.getNewId());
    }

    public void loadAllData() throws IOException {
        tblStudent.setItems(studentBO.getAllStudent());
    }

    public void txtKeyReleased(KeyEvent keyEvent) throws IOException {
        validation();

        if (keyEvent.getCode()== KeyCode.ENTER){
            Object response = validation();

            if (response instanceof JFXTextField){
               JFXTextField textField= (JFXTextField) response;
               textField.requestFocus();
            }else if (response instanceof Boolean){
                saveStudent();
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

    public void saveStudent() throws IOException {
        StudentDTO s = new StudentDTO(txtStudentId.getText(),txtName.getText(),txtAddress.getText(),txtDOB.getText(),txtGender.getText(),txtContactNo.getText());
        if (studentBO.saveStudent(s)) {
            new Alert(Alert.AlertType.CONFIRMATION,"Saved!!!").show();
        }


        loadAllData();
        generateId();
        clearTexts();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws IOException {
        saveStudent();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        StudentDTO s = new StudentDTO(txtUpdateStudentId.getText(),txtUpdateName.getText(),txtUpdateAddress.getText(),txtUpdateDOB.getText(),txtUpdateGender.getText(),txtUpdateContactNo.getText());
        if (studentBO.updateStudent(s)) {
            new Alert(Alert.AlertType.CONFIRMATION,"Updated!!!").show();
        }

        loadAllData();
        clearUpdateTexts();
    }


    public void btnDeleteOnAction(ActionEvent actionEvent) throws IOException {
        if (studentBO.deleteStudent(txtUpdateStudentId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted!!!").show();
        }

        loadAllData();
        generateId();
        clearUpdateTexts();
    }

    public void clearTexts(){
        txtName.clear();
        txtAddress.clear();
        txtDOB.clear();
        txtGender.clear();
        txtContactNo.clear();

        txtName.requestFocus();
        btnSave.setDisable(true);

        txtName.setFocusColor(Paint.valueOf("#4059a9"));
        txtAddress.setFocusColor(Paint.valueOf("#4059a9"));
        txtDOB.setFocusColor(Paint.valueOf("#4059a9"));
        txtGender.setFocusColor(Paint.valueOf("#4059a9"));
        txtContactNo.setFocusColor(Paint.valueOf("#4059a9"));
    }


    private void clearUpdateTexts() {
        txtUpdateStudentId.clear();
        txtUpdateName.clear();
        txtUpdateAddress.clear();
        txtUpdateDOB.clear();
        txtUpdateGender.clear();
        txtUpdateContactNo.clear();

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }


}
