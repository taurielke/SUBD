package logic;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;
import java.util.Scanner;

public class ProductLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Insert 1 to create product");
        System.out.println("Insert 2 to read product");
        System.out.println("Insert 3 to  update product");
        System.out.println("Insert 4 to delete product");
        System.out.println("Insert 5 to filter");
        System.out.println("Insert 6 to get list of material in product");

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
            case 6:
                materialList(session);
                break;
        }
        session.getTransaction().commit();
    }

    private void create(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert product name");
        String nameProduct = scanner.next();
        System.out.println("Insert warehouse quantity");
        int amount = scanner.nextInt();
        Product product = new Product(nameProduct, amount);
        session.save(product);
    }

    private void read(Session session) {
        List<Product> products = session.createQuery("SELECT a from Product a", Product.class).getResultList();
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
                System.out.println("Insert amount");
                int amount = scanner.nextInt();
                Product_Material product_material = new Product_Material(id, materialID, amount);
                session.save(product_material);
                break;
            case 3:
                System.out.println("Insert product name");
                String nameProduct1 = scanner.next();
                product.setProductName(nameProduct1);
                System.out.println("Insert material id");
                int materialID1 = scanner.nextInt();
                System.out.println("Insert amount");
                int amount1 = scanner.nextInt();
                Product_Material product_material1 = new Product_Material(id, materialID1, amount1);
                session.save(product);
                session.save(product_material1);
                break;
        }
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert product id");
        int id = scanner.nextInt();
        Product product = session.get(Product.class, id);
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
        }
        catch (Exception ex){
            System.out.println("cannot be deleted because it is in another entity");
        }

    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert product name");
        String nameProduct = scanner.next();
        List<Product> products = session.createQuery("SELECT a from Product a WHERE productName = \'" + nameProduct + "\'", Product.class).getResultList();
        System.out.println(products);
    }

    private void materialList(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert product ID");
        int productID = scanner.nextInt();
        List<Product_Material> materials = session.createQuery("SELECT a from Product_Material a WHERE productID = \'" + productID + "\'", Product_Material.class).getResultList();
        System.out.println(materials);
    }
}