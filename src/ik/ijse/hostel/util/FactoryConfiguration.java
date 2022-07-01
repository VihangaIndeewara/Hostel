package ik.ijse.hostel.util;

import ik.ijse.hostel.bo.custom.RoomBO;
import ik.ijse.hostel.entity.Reservation;
import ik.ijse.hostel.entity.Room;
import ik.ijse.hostel.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;



    private FactoryConfiguration() throws IOException {
        Configuration configuration = new Configuration();

        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("ik/ijse/hostel/resources/hibernate.properties"));

        configuration.setProperties(properties)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Reservation.class);

        sessionFactory=configuration.buildSessionFactory();

    }

    public static FactoryConfiguration getInstance() throws IOException {
        if (factoryConfiguration==null){
            factoryConfiguration=new FactoryConfiguration();
        }
            return factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
