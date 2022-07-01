package ik.ijse.hostel.dao.custom;

import ik.ijse.hostel.dao.CrudDAO;
import ik.ijse.hostel.entity.Reservation;
import javafx.collections.ObservableList;

import java.io.IOException;

public interface ReservationDAO extends CrudDAO<Reservation,String > {
    ObservableList<Reservation> getAllReservationDetails() throws IOException;

    public boolean updatePayingAmount(double amount,String id) throws IOException;
}
