package ik.ijse.hostel.bo.custom;

import ik.ijse.hostel.bo.SuperBO;
import ik.ijse.hostel.dto.ReservationDTO;
import ik.ijse.hostel.entity.Reservation;
import javafx.collections.ObservableList;

import java.io.IOException;

public interface ReservationBO extends SuperBO {
    String getNewId() throws IOException;

    boolean registerNewReservation(ReservationDTO dto) throws IOException;

    ObservableList<ReservationDTO> getAllReservationDetails() throws  IOException;

     boolean updatePayingAmount(double amount,String id) throws IOException;
}
