package logic;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class ProductLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Insert 1 to create product");
        System.out.println("Insert 2 to read product");
        System.out.println("Insert 3 to  update product");
        System.out.println("Insert 4 to delete product");
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
        System.out.println("Insert product name");
        String nameProduct = scanner.next();
        Product product = new Product(nameProduct);
        session.save(product);
    }

    private void read(Session session) {
        List<Product> products = session.createQuery("SELECT Main from Product a", Product.class).getResultList();
        System.out.println(products);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert product id");
        int id = scanner.nextInt();
        Product product = session.get(Product.class, id);

        System.out.println("Insert 1 to change name");
        System.out.println("Insert 2 to add material");
        System.out.println("Insert 3 to do both");


        int choice = scanner.nextInt();
        switch(choice){
            case 1:
                System.out.println("Insert product name");
                String nameProduct = scanner.next();
                product.setProductName(nameProduct);
                session.save(product);
                break;
            case 2:
                System.out.println("Insert material id");
                int materialID = scanner.nextInt();
                /*product.setMaterial(session.get(Material.class, materialID));*/
                session.save(product);
                break;
            case 3:
                System.out.println("Insert product name");
                String nameProduct1 = scanner.next();
                product.setProductName(nameProduct1);
                System.out.println("Insert material id");
                int materialID1 = scanner.nextInt();
                /*product.setMaterial(session.get(Material.class, materialID1));*/
                session.save(product);
                break;
        }
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert product id");
        int id = scanner.nextInt();
        Product product = session.get(Product.class, id);
        session.delete(product);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert product name");
        String nameProduct = scanner.next();
        List<Product> products = session.createQuery("SELECT Main from Product a WHERE productName = \'" + nameProduct + "\'", Product.class).getResultList();
        System.out.println(products);
    }
}