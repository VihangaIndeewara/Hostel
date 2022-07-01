package ik.ijse.hostel.bo.custom;

import ik.ijse.hostel.bo.SuperBO;
import ik.ijse.hostel.dto.RoomDTO;
import ik.ijse.hostel.view.TM.CartTM;
import javafx.collections.ObservableList;

import java.io.IOException;

public interface RoomBO extends SuperBO {

    boolean saveRoom(RoomDTO dto) throws IOException;

    boolean updateRoom(RoomDTO dto) throws IOException;

    boolean deleteRoom(String id) throws IOException;

    ObservableList<RoomDTO> getAllRoom() throws IOException;

    ObservableList<String> getAllRoomTypeIds() throws IOException;

    ObservableList<RoomDTO> getRoomsData(String id) throws IOException;

    boolean updateRoomQty(String id)throws IOException;
}
