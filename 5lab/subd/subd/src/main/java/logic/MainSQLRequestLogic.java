package logic;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;


public class MainSQLRequestLogic {
    public void work (SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Operation> operations = session.createQuery("SELECT a FROM Operation a",
                Operation.class).getResultList();
        System.out.println(" Date\t\t\t\tStatus\t\t\tProduct");
        for (int i = 0; i < operations.size(); i++) {
            System.out.format("\n%s\t\t\t%s\t\t\t%s", operations.get(i).getDate().toString(),
                    operations.get(i).getStatus_product().getStatusName(), operations.get(i).getProduct().getProductName());
        }
        session.getTransaction().commit();
    }
}
