package ik.ijse.hostel.bo.custom.impl;

import ik.ijse.hostel.bo.custom.RoomBO;
import ik.ijse.hostel.dao.DAOFactory;
import ik.ijse.hostel.dao.SuperDAO;
import ik.ijse.hostel.dao.custom.RoomDAO;
import ik.ijse.hostel.dao.custom.impl.RoomDAOImpl;
import ik.ijse.hostel.dto.RoomDTO;
import ik.ijse.hostel.entity.Room;
import ik.ijse.hostel.view.TM.CartTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public boolean saveRoom(RoomDTO dto) throws IOException {
        return roomDAO.save(new Room(dto.getRoomTypeId(),dto.getType(),dto.getKeyMoney(),dto.getQty()));
    }

    @Override
    public boolean updateRoom(RoomDTO dto) throws IOException {
        return roomDAO.update(new Room(dto.getRoomTypeId(),dto.getType(),dto.getKeyMoney(),dto.getQty()));
    }

    @Override
    public boolean deleteRoom(String id) throws IOException {
        return roomDAO.delete(id);
    }

    @Override
    public ObservableList<RoomDTO> getAllRoom() throws IOException {
        ObservableList<Room> all = roomDAO.getAll();
        ObservableList<RoomDTO> roomList = FXCollections.observableArrayList();

        for (Room room : all) {
            roomList.add(new RoomDTO(room.getRoomTypeId(),room.getType(),room.getKeyMoney(),room.getQty()));
        }
        return roomList;
    }

    @Override
    public ObservableList<String> getAllRoomTypeIds() throws IOException {
        return roomDAO.getRoomTypeIds();
    }

    @Override
    public ObservableList<RoomDTO> getRoomsData(String id) throws IOException {
        ObservableList<Room> roomData = roomDAO.getRoomData(id);

        ObservableList<RoomDTO> roomDataList=FXCollections.observableArrayList();

        for (Room r : roomData) {
            roomDataList.add(new RoomDTO(r.getRoomTypeId(),r.getType(),r.getKeyMoney(),r.getQty()));
        }
        return roomDataList;
    }

    @Override
    public boolean updateRoomQty(String id)throws IOException {
       return roomDAO.updateRoomQty(id);
    }
}
