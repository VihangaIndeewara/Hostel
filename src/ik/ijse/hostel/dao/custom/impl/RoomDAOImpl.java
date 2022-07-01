package ik.ijse.hostel.dao.custom.impl;

import ik.ijse.hostel.dao.custom.RoomDAO;
import ik.ijse.hostel.dto.RoomDTO;
import ik.ijse.hostel.entity.Room;
import ik.ijse.hostel.util.FactoryConfiguration;
import ik.ijse.hostel.view.TM.CartTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean save(Room room) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(room);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Room room) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(room);

        transaction.commit();
        session.close();


        return true;
    }

    @Override
    public boolean delete(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = session.get(Room.class, id);
        session.delete(room);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public ObservableList<Room> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="FROM Room";
        List<Room> list = session.createQuery(hql).list();

        ObservableList<Room> roomList = FXCollections.observableArrayList();

        for (Room room : list) {
            roomList.add(new Room(
                    room.getRoomTypeId(),
                    room.getType(),
                    room.getKeyMoney(),
                    room.getQty()
            ));
        }

        transaction.commit();
        session.close();

        return roomList;
    }

    @Override
    public String generateNewId() {
       return null;
    }

    @Override
    public ObservableList<String> getRoomTypeIds() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        ObservableList<String> idList =FXCollections.observableArrayList();

        String hql="SELECT roomTypeId FROM Room ";
        List<String> list = session.createQuery(hql).list();

        for (String id : list) {
            idList.add(id);
        }


        transaction.commit();
        session.close();
        return idList;
    }

    @Override
    public ObservableList<Room> getRoomData(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="FROM Room WHERE roomTypeId=:typeId";
        Query query = session.createQuery(hql);
        query.setParameter("typeId",id);
        List<Room> list = query.list();

        ObservableList<Room> roomsDataList =FXCollections.observableArrayList();

        for (Room room : list) {
            roomsDataList.add(new Room(room.getRoomTypeId(),room.getType(),room.getKeyMoney(),room.getQty()));
        }

        transaction.commit();
        session.close();

        return roomsDataList;
    }

    @Override
    public boolean updateRoomQty(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="UPDATE Room SET qty=qty-1 WHERE roomTypeId=:type_id";
        Query query = session.createQuery(hql);
        query.setParameter("type_id",id);
        query.executeUpdate();

        transaction.commit();
        session.close();

        return true;
    }
}
