package ik.ijse.hostel.dao.custom;

import ik.ijse.hostel.dao.CrudDAO;
import ik.ijse.hostel.entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public interface StudentDAO extends CrudDAO<Student,String> {
    ObservableList<String> studentIdList() throws IOException;

    String getName(String id) throws IOException;
}
