package ik.ijse.hostel.bo;

import ik.ijse.hostel.bo.custom.impl.ReservationBOImpl;
import ik.ijse.hostel.bo.custom.impl.RoomBOImpl;
import ik.ijse.hostel.bo.custom.impl.StudentBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getInstance(){
        if (boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes{
        STUDENT,ROOM,RESERVATION
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case RESERVATION:
                return new ReservationBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            default:
                return null;
        }
    }
}
