package logic;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class OrderLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Введите 1 для создания заказа");
        System.out.println("Введите 2 для чтения заказа");
        System.out.println("Введите 3 для изменения заказа");
        System.out.println("Введите 4 для удаления заказа");
        System.out.println("Введите 5 для фильтра");

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
        System.out.println("Введите дедлайн");
        String date = scanner.next();
        java.util.Date myDate = new java.util.Date(date);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        System.out.println("Введите ФИО покупателя");
        String buyerName = scanner.next();

        System.out.println("Введите телефонный номер покупателя");
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

        System.out.println("Ввдение id заказа");
        int id = scanner.nextInt();

        System.out.println("Введите дедлайн");
        String date = scanner.next();
        java.util.Date myDate = new java.util.Date(date);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        System.out.println("Введите ФИО покупателя");
        String buyerName = scanner.next();

        System.out.println("Введите телефонный номер покупателя");
        int phoneNumber = scanner.nextInt();

        Orders order = session.get(Orders.class, id);
        order.setDeadline(sqlDate);
        order.setBuyerName(buyerName);
        order.setBuyerPhoneNumber(phoneNumber);
        session.save(order);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ввдение id заказа");
        int id = scanner.nextInt();

        Orders order = session.get(Orders.class, id);
        session.delete(order);
    }

    private void filterRead(Session session) {
        System.out.println("Введите 1 для фильтра по дедлайну");
        System.out.println("Введите 2 для фильтра по имени покупателя");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Orders> orders = null;
        switch(i) {
            case 1:
                System.out.println("Введите дедлайн");
                String date = scanner.next();
                java.util.Date myDate = new java.util.Date(date);
                java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
                orders = session.createQuery("SELECT a from Orders a where deadline = \'" + sqlDate + "\'", Orders.class).getResultList();
                break;
            case 2:
                System.out.println("Введите имя покупателя");
                String name = scanner.next();
                orders = session.createQuery("SELECT a from Orders a where buyerName = " + name, Orders.class).getResultList();
                break;
        }
        System.out.println(orders);
    }
}