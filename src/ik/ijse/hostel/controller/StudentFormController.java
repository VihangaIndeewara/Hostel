package ik.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import ik.ijse.hostel.dto.StudentDTO;
import ik.ijse.hostel.entity.Student;
import ik.ijse.hostel.util.FactoryConfiguration;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;

public class StudentFormController {
    public AnchorPane AnchorPaneContext;
    public TableView tblStudent;
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

    public void initialize(){
        generateId();
    }

    private void generateId() {
        Session session = FactoryConfiguration.getInstance().getSession();




        session.close();
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        StudentDTO s = new StudentDTO(txtStudentId.getText(),txtName.getText(),txtAddress.getText(),txtDOB.getText(),txtGender.getText(),txtContactNo.getText());
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }


    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }



    public void txtKeyReleased(KeyEvent keyEvent) {
    }


}
