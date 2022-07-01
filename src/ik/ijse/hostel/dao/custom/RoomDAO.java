package ik.ijse.hostel.dao.custom;

import ik.ijse.hostel.dao.CrudDAO;
import ik.ijse.hostel.entity.Room;
import ik.ijse.hostel.view.TM.CartTM;
import javafx.collections.ObservableList;

import java.io.IOException;

public interface RoomDAO extends CrudDAO<Room,String> {
    ObservableList<String> getRoomTypeIds() throws IOException;

    ObservableList<Room> getRoomData(String id) throws IOException;

    boolean updateRoomQty(String id) throws IOException;
}
