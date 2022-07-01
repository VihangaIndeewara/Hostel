package ik.ijse.hostel.dao;

import ik.ijse.hostel.dao.custom.impl.QueryDAOImpl;
import ik.ijse.hostel.dao.custom.impl.ReservationDAOImpl;
import ik.ijse.hostel.dao.custom.impl.RoomDAOImpl;
import ik.ijse.hostel.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        if (daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes{
        STUDENT,ROOM,RESERVATION,QUERY
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case ROOM:
                return new RoomDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            case QUERY:
                return  new QueryDAOImpl();
            default:
                return  null;
        }
    }
}
