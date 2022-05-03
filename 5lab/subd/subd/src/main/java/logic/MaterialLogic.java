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
        List<Material> materials = session.createQuery("SELECT a from Material a", Material.class).getResultList();
        System.out.println(materials);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert material id");
        int id = scanner.nextInt();

        System.out.println("Insert 1 to change name");
        System.out.println("Insert 2 to change warehouse quantity");
        System.out.println("Insert 3 to do both");

        int choice = scanner.nextInt();
        switch(choice){
            case 1:
                System.out.println("Insert material name");
                String name = scanner.next();
                Material material = session.get(Material.class, id);
                material.setMaterialName(name);
                session.save(material);
                break;
            case 2:
                System.out.println("Insert warehouse quantity of the material");
                int amount = scanner.nextInt();
                Material material1 = session.get(Material.class, id);
                material1.setWarehouse_quantity(amount);
                session.save(material1);
                break;
            case 3:
                System.out.println("Insert material name");
                String name2 = scanner.next();
                System.out.println("Insert warehouse quantity of the material");
                int amount2 = scanner.nextInt();
                Material material2 = session.get(Material.class, id);
                material2.setMaterialName(name2);
                material2.setWarehouse_quantity(amount2);
                session.save(material2);
                break;
        }
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
        List<Material> materials = session.createQuery("SELECT a from Material a WHERE materialName = \'" + name + "\'", Material.class).getResultList();
        System.out.println(materials);
    }
}