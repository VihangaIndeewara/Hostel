package ik.ijse.hostel.bo.custom;

import ik.ijse.hostel.bo.SuperBO;
import ik.ijse.hostel.dto.StudentDTO;
import javafx.collections.ObservableList;

import java.io.IOException;

public interface StudentBO extends SuperBO {
    String getNewId() throws IOException;

    boolean saveStudent(StudentDTO dto) throws IOException;

    boolean updateStudent(StudentDTO dto) throws IOException;

    boolean deleteStudent(String id) throws IOException;

    ObservableList<StudentDTO> getAllStudent() throws IOException;

    ObservableList<String> getAllStudentIds() throws IOException;

    String getStudentName(String id) throws IOException;

}
