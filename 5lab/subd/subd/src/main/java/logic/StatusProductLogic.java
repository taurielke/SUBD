package logic;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class StatusProductLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Insert 1 to create status");
        System.out.println("Insert 2 to read status");
        System.out.println("Insert 3 to  update status");
        System.out.println("Insert 4 to delete status");
        System.out.println("Insert 5 to filter");

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        Session session = null;
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        switch (i) {
            case 1:
                create(session);
                break;
            case 2:
                read(session);
                break;
            case 3:
                update(session);
                break;
            case 4:
                delete(session);
                break;
            case 5:
                filterRead(session);
                break;
        }
        session.getTransaction().commit();
    }

    private void create(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert status name");
        String nameStatus = scanner.next();
        Status_Product status = new Status_Product(nameStatus);
        session.save(status);
    }

    private void read(Session session) {
        List<Status_Product> statuses = session.createQuery("SELECT a from Status_Product a",
                Status_Product.class).getResultList();
        System.out.println(statuses);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert status id");
        int id = scanner.nextInt();

        System.out.println("Insert status name");
        String nameStatus = scanner.next();

        Status_Product status = session.get(Status_Product.class, id);
        status.setStatusName(nameStatus);
        session.save(status);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert status id");
        int id = scanner.nextInt();
        Status_Product status_product = session.get(Status_Product.class, id);
        session.delete(status_product);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert status name");
        String nameStatus = scanner.next();
        List<Status_Product> statuses = session.createQuery("SELECT a from Status_Product a where " +
                "statusName = \'" + nameStatus + "\'", Status_Product.class).getResultList();
        System.out.println(statuses);
    }
}