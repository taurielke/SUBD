package logic;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class ProductLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Введите 1 для создания изделия");
        System.out.println("Введите 2 для чтения изделия");
        System.out.println("Введите 3 для изменения изделия");
        System.out.println("Введите 4 для удаления изделия");
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
        System.out.println("Введите название изделия");
        String nameProduct = scanner.next();
        Product product = new Product(nameProduct);
        session.save(product);
    }

    private void read(Session session) {
        List<Product> products = session.createQuery("SELECT a from Product a", Product.class).getResultList();
        System.out.println(products);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id изделия");
        int id = scanner.nextInt();
        Product product = session.get(Product.class, id);

        System.out.println("Введите 1 для изменения названия");
        System.out.println("Введите 2 для добавления материала");
        System.out.println("Введите 3 для изменения названия и добавления материала");


        int choice = scanner.nextInt();
        switch(choice){
            case 1:
                System.out.println("Введите название изделия");
                String nameProduct = scanner.next();
                product.setProductName(nameProduct);
                session.save(product);
                break;
            case 2:
                System.out.println("Введите id материала");
                int materialID = scanner.nextInt();
                product.setMaterial(session.get(Material.class, materialID));
                session.save(product);
                break;
            case 3:
                System.out.println("Введите название изделия");
                String nameProduct1 = scanner.next();
                product.setProductName(nameProduct1);
                System.out.println("Введите id материала");
                int materialID1 = scanner.nextInt();
                product.setMaterial(session.get(Material.class, materialID1));
                session.save(product);
                break;
        }
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id изделия");
        int id = scanner.nextInt();
        Product product = session.get(Product.class, id);
        session.delete(product);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название изделия");
        String nameProduct = scanner.next();
        List<Product> products = session.createQuery("SELECT a from Product a WHERE productName = \'" + nameProduct + "\'", Product.class).getResultList();
        System.out.println(products);
    }
}