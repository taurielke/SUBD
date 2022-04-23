package logic;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class OperationLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Введите 1 для создания доходов");
        System.out.println("Введите 2 для чтения доходов");
        System.out.println("Введите 3 для изменения доходов");
        System.out.println("Введите 4 для удаления доходов");
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

        System.out.println("Введите дату операции");
        String date = scanner.next();
        java.util.Date myDate = new java.util.Date(date);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        System.out.println("Введите id изделия");
        int productID = scanner.nextInt();

        System.out.println("Введите кол-во изделия");
        int amount = scanner.nextInt();

        System.out.println("Введите id статуса");
        int statusID = scanner.nextInt();

        System.out.println("Введите id заказа");
        int orderID = scanner.nextInt();

        Operation operation = new Operation(sqlDate, session.get(Product.class, productID), amount,
                session.get(Status_Product.class, statusID), session.get(Orders.class, orderID));

        session.save(operation);
    }

    private void read(Session session) {
        List<Operation> operations = session.createQuery("SELECT a from Operation a", Operation.class).getResultList();
        System.out.println(operations);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id операции");
        int id = scanner.nextInt();

        System.out.println("Введите дату операции");
        String date = scanner.next();
        java.util.Date myDate = new java.util.Date(date);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        System.out.println("Введите id изделия");
        int productID = scanner.nextInt();

        System.out.println("Введите кол-во изделия");
        int amount = scanner.nextInt();

        System.out.println("Введите id статуса");
        int statusID = scanner.nextInt();

        System.out.println("Введите id заказа");
        int orderID = scanner.nextInt();

        Operation operation = session.get(Operation.class, id);
        operation.setDate(sqlDate);
        operation.setProduct(session.get(Product.class, productID));
        operation.setQuantityProduct(amount);
        operation.setStatus_product(session.get(Status_Product.class, statusID));
        operation.setOrder(session.get(Orders.class, orderID));
        session.save(operation);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ввдение id операции");
        int id = scanner.nextInt();
        Operation operation = session.get(Operation.class, id);
        session.delete(operation);
    }

    private void filterRead(Session session) {
        System.out.println("Введите 1 для фильтра по дате операции");
        System.out.println("Введите 2 для фильтра по изделию");
        System.out.println("Введите 3 для фильтра по кол-ву изделия");
        System.out.println("Введите 4 для фильтра по статусу");
        System.out.println("Введите 5 для фильтра по номеру заказа");

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Operation> operations = null;
        switch(i) {
            case 1:
                System.out.println("Введите дату операции");
                String date = scanner.next();
                java.util.Date myDate = new java.util.Date(date);
                java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
                operations = session.createQuery("SELECT a from Operation a where date = \'"
                        + sqlDate + "\'", Operation.class).getResultList();
                break;
            case 2:
                System.out.println("Введите id изделия");
                int productID = scanner.nextInt();
                operations = session.createQuery("SELECT a from Operation a where product = "
                        + productID, Operation.class).getResultList();
                break;
            case 3:
                System.out.println("Введите кол-во изделия");
                int amount = scanner.nextInt();
                operations = session.createQuery("SELECT a from Operation a where quantityProduct = \'"
                        + amount + "\'", Operation.class).getResultList();
                break;
            case 4:
                System.out.println("Введите id статуса");
                int statusID = scanner.nextInt();
                operations = session.createQuery("SELECT a from Operation a where status_product = "
                        + statusID, Operation.class).getResultList();
                break;
            case 5:
                System.out.println("Введите id заказа");
                int orderID = scanner.nextInt();
                operations = session.createQuery("SELECT a from Operation a where order = "
                        + orderID, Operation.class).getResultList();
                break;
        }
        System.out.println(operations);
    }
}