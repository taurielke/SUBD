package logic;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class MaterialLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Insert 1 to create material");
        System.out.println("Insert 2 to read material");
        System.out.println("Insert 3 to  update material");
        System.out.println("Insert 4 to delete material");
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
        System.out.println("Insert material name");
        String name = scanner.next();
        System.out.println("Insert warehouse quantity of the material");
        int amount = scanner.nextInt();
        Material material = new Material(name, amount);
        session.save(material);
    }

    private void read(Session session) {
        List<Material> materials = session.createQuery("SELECT Main from Material a", Material.class).getResultList();
        System.out.println(materials);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert material id");
        int id = scanner.nextInt();
        System.out.println("Insert material name");
        String name = scanner.next();
        System.out.println("Insert warehouse quantity of the material");
        int amount = scanner.nextInt();
        Material material = session.get(Material.class, id);
        material.setMaterialName(name);
        material.setWarehouse_quantity(amount);
        session.save(material);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert material id");
        int id = scanner.nextInt();
        Material material = session.get(Material.class, id);
        session.delete(material);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert material name");
        String name = scanner.next();
        List<Material> materials = session.createQuery("SELECT Main from Material a WHERE materialName = \'" + name + "\'", Material.class).getResultList();
        System.out.println(materials);
    }
}