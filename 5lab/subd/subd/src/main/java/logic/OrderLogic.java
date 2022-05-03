package logic;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class OrderLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Insert 1 to create order");
        System.out.println("Insert 2 to read order");
        System.out.println("Insert 3 to  update order");
        System.out.println("Insert 4 to delete order");
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
        System.out.println("Insert deadline");
        String date = scanner.next();
        java.util.Date myDate = new java.util.Date(date);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        System.out.println("Insert buyer's name");
        String buyerName = scanner.next();

        System.out.println("Insert buyer's phone number");
        int phoneNumber = scanner.nextInt();

        Orders order = new Orders(sqlDate, buyerName, phoneNumber);

        session.save(order);
    }

    private void read(Session session) {
        List<Orders> orders = session.createQuery("SELECT a from Orders a", Orders.class).getResultList();
        System.out.println(orders);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert order id");
        int id = scanner.nextInt();

        System.out.println("Insert deadline");
        String date = scanner.next();
        java.util.Date myDate = new java.util.Date(date);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        System.out.println("Insert buyer's name");
        String buyerName = scanner.next();

        System.out.println("Insert buyer's phone number");
        int phoneNumber = scanner.nextInt();

        Orders order = session.get(Orders.class, id);
        order.setDeadline(sqlDate);
        order.setBuyerName(buyerName);
        order.setBuyerPhoneNumber(phoneNumber);
        session.save(order);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert order id");
        int id = scanner.nextInt();

        Orders order = session.get(Orders.class, id);
        session.delete(order);
    }

    private void filterRead(Session session) {
        System.out.println("Insert 1 to filter by deadline");
        System.out.println("Insert 2 to filter by buyer's name");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Orders> orders = null;
        switch(i) {
            case 1:
                System.out.println("Insert deadline");
                String date = scanner.next();
                java.util.Date myDate = new java.util.Date(date);
                java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
                orders = session.createQuery("SELECT a from Orders a where deadline = \'" + sqlDate + "\'", Orders.class).getResultList();
                break;
            case 2:
                System.out.println("Insert buyer's name");
                String name = scanner.next();
                orders = session.createQuery("SELECT a from Orders a where buyerName = " + name, Orders.class).getResultList();
                break;
        }
        System.out.println(orders);
    }
}