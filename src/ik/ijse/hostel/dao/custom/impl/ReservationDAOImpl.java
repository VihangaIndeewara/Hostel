package ik.ijse.hostel.dao.custom.impl;

import ik.ijse.hostel.dao.custom.ReservationDAO;
import ik.ijse.hostel.entity.Reservation;
import ik.ijse.hostel.entity.Student;
import ik.ijse.hostel.util.FactoryConfiguration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public boolean save(Reservation entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Reservation reservation) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public ObservableList<Reservation> getAll() {
        return null;
    }

    @Override
    public String generateNewId() throws IOException {
        String newId="R001";
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String sql="SELECT resId FROM Reservation ORDER BY resId DESC LIMIT 1";
        List<String> list = session.createSQLQuery(sql).list();

        for (String id : list) {
            if (id!=null){
                int num = Integer.valueOf(id.substring(1));
                num++;

                if (num<=9){
                    newId="R00"+num;
                }else if (num>9&&num<100){
                    newId="R0"+num;
                }else if (num>=100){
                    newId="R"+num;
                }
            }
        }

        transaction.commit();
        session.close();

        return newId;
    }

    @Override
    public ObservableList<Reservation>  getAllReservationDetails() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="FROM Reservation";
        List<Reservation> list = session.createQuery(hql).list();

        ObservableList<Reservation> allList= FXCollections.observableArrayList();


        for (Reservation r : list) {
            allList.add(new Reservation(
                    r.getResId(),
                    r.getType(),
                    r.getKeyMoney(),
                    r.getPayingAmount(),
                    r.getDateFrom(),
                    r.getDateTo(),
                    r.getStudent().getStudentId(),
                    r.getRoom().getRoomTypeId()
            ));
        }

        transaction.commit();
        session.close();
        return allList;
    }

    @Override
    public boolean updatePayingAmount(double amount,String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="UPDATE Reservation SET payingAmount=:amount1 WHERE resId=:rId";
        Query query = session.createQuery(hql);
        query.setParameter("amount1",amount);
        query.setParameter("rId",id);
        query.executeUpdate();

        transaction.commit();
        session.close();

        return true;
    }
}
