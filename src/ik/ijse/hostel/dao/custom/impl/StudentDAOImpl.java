package ik.ijse.hostel.dao.custom.impl;

import ik.ijse.hostel.dao.custom.StudentDAO;
import ik.ijse.hostel.entity.Student;
import ik.ijse.hostel.util.FactoryConfiguration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO{
    @Override
    public boolean save(Student entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean delete(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student s = session.get(Student.class, id);
        session.delete(s);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public ObservableList<Student> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="FROM Student";
        List<Student> list = session.createQuery(hql).list();

        ObservableList<Student> obList = FXCollections.observableArrayList();

        for (Student s : list) {
            obList.add(new Student(
                    s.getStudentId(),
                    s.getName(),
                    s.getAddress(),
                    s.getDob(),
                    s.getGender(),
                    s.getContactNo()
            ));

        }

        transaction.commit();
        session.close();

        return obList;
    }

    @Override
    public String generateNewId() throws IOException {
        String newStudentId ="S001";
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String sql="SELECT studentId FROM Student ORDER BY studentId DESC LIMIT 1";
        List<String> list = session.createSQLQuery(sql).list();


        for (String id : list) {
            if (id!=null){
                int num = Integer.valueOf(id.substring(1));
                num++;

                if (num<=9){
                    newStudentId="S00"+num;
                }else if (num>9&&num<100){
                    newStudentId="S0"+num;
                }else if (num>=100){
                    newStudentId="S"+num;
                }
            }
        }

        transaction.commit();
        session.close();
        return  newStudentId;
    }

    @Override
    public ObservableList<String> studentIdList() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        ObservableList<String> idList =FXCollections.observableArrayList();

        String hql="SELECT studentId FROM Student";
        List<String> list = session.createQuery(hql).list();

        for (String id : list) {
            idList.add(id);
        }

        transaction.commit();
        session.close();
        return idList;
    }

    @Override
    public String getName(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="SELECT name FROM Student WHERE studentId=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        List<String> list = query.list();

        String name="";

        for (String s : list) {
            name=s;
        }

        transaction.commit();
        session.close();


        return name;
    }
}
