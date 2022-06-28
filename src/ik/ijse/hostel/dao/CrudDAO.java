package ik.ijse.hostel.dao;

import javafx.collections.ObservableList;

public interface CrudDAO<T,id> {
    boolean save(T t);

    boolean update(T t);

    boolean delete(id id);

    ObservableList<T> getAll();

    String generateNewId();
}
