package ik.ijse.hostel.bo.custom.impl;

import ik.ijse.hostel.bo.custom.StudentBO;
import ik.ijse.hostel.dao.DAOFactory;
import ik.ijse.hostel.dao.custom.RoomDAO;
import ik.ijse.hostel.dao.custom.StudentDAO;
import ik.ijse.hostel.dao.custom.impl.StudentDAOImpl;
import ik.ijse.hostel.dto.StudentDTO;
import ik.ijse.hostel.entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public String getNewId() throws IOException {
        return studentDAO.generateNewId();
    }

    @Override
    public boolean saveStudent(StudentDTO dto) throws IOException {
      return studentDAO.save(new Student(
              dto.getStudentId(),
              dto.getName(),
              dto.getAddress(),
              dto.getDob(),
              dto.getGender(),
              dto.getContactNo()
      ));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws IOException {
      return studentDAO.update(new Student(dto.getStudentId(), dto.getName(), dto.getAddress(), dto.getDob(), dto.getGender(), dto.getContactNo()));
    }

    @Override
    public boolean deleteStudent(String id) throws IOException {
        return studentDAO.delete(id);
    }

    @Override
    public ObservableList<StudentDTO> getAllStudent() throws IOException {
        ObservableList<Student> all = studentDAO.getAll();
        ObservableList<StudentDTO> list = FXCollections.observableArrayList();

        for (Student s : all) {
               list.add(new StudentDTO(
                       s.getStudentId(),
                       s.getName(),
                       s.getAddress(),
                       s.getDob(),
                       s.getGender(),
                       s.getContactNo()
               ));
        }


        return list;
    }

    @Override
    public ObservableList<String> getAllStudentIds() throws IOException {
       return studentDAO.studentIdList();
    }

    @Override
    public String getStudentName(String id) throws IOException {
        return studentDAO.getName(id);
    }
}
