package ik.ijse.hostel.dao.custom.impl;

import ik.ijse.hostel.dao.custom.StudentDAO;
import ik.ijse.hostel.entity.Student;
import ik.ijse.hostel.util.FactoryConfiguration;
import javafx.collections.ObservableList;
import org.hibernate.Session;

public class StudentDAOImpl implements StudentDAO{
    @Override
    public boolean save(Student entity) {
        return false;
    }

    @Override
    public boolean update(Student entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public ObservableList<Student> getAll() {
        return null;
    }

    @Override
    public String generateNewId() {

    }
}
