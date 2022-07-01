package ik.ijse.hostel.dao;

import javafx.collections.ObservableList;

import java.io.IOException;

public interface CrudDAO<T,id> extends SuperDAO {
    boolean save(T t) throws IOException;

    boolean update(T t) throws IOException;

    boolean delete(id id) throws IOException;

    ObservableList<T> getAll() throws IOException;

    String generateNewId() throws IOException;
}
