package ik.ijse.hostel.bo.custom.impl;

import ik.ijse.hostel.bo.custom.ReservationBO;
import ik.ijse.hostel.dao.DAOFactory;
import ik.ijse.hostel.dao.custom.ReservationDAO;
import ik.ijse.hostel.dao.custom.RoomDAO;
import ik.ijse.hostel.dao.custom.impl.ReservationDAOImpl;
import ik.ijse.hostel.dto.ReservationDTO;
import ik.ijse.hostel.entity.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;


public class ReservationBOImpl implements ReservationBO {
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RESERVATION);

    @Override
    public String getNewId() throws IOException {
        return reservationDAO.generateNewId();
    }

    @Override
    public boolean registerNewReservation(ReservationDTO dto) throws IOException {
        return reservationDAO.save(new Reservation(dto.getResId(),dto.getType(),dto.getKeyMoney(),dto.getPayingAmount(),dto.getDateFrom(),dto.getDateTo(),dto.getStudentId(),dto.getRoomId()));
    }

    @Override
    public ObservableList<ReservationDTO> getAllReservationDetails() throws IOException {
        ObservableList<Reservation> allReservationDetails = reservationDAO.getAllReservationDetails();

        ObservableList<ReservationDTO> list =FXCollections.observableArrayList();

        for (Reservation r : allReservationDetails) {
            list.add(new ReservationDTO(
                    r.getResId(),
                    r.getType(),
                    r.getKeyMoney(),
                    r.getPayingAmount(),
                    r.getDateFrom(),
                    r.getDateTo(),
                    r.getSId(),
                    r.getRId()
            ));
        }
        return  list;
    }

    @Override
    public boolean updatePayingAmount(double amount, String id) throws IOException {
        return reservationDAO.updatePayingAmount(amount,id);
    }


}
