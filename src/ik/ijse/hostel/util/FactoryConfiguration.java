package ik.ijse.hostel.util;

import ik.ijse.hostel.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Configuration configure = new Configuration().configure().addAnnotatedClass(Student.class);
        sessionFactory=configure.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        if (factoryConfiguration==null){
            factoryConfiguration=new FactoryConfiguration();
        }
            return factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
