import jdk.jshell.Snippet;
import logic.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import models.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.close();
//        HibernateUtil.shutdown();

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Material.class)
                .addAnnotatedClass(Operation.class)
                .addAnnotatedClass(Orders.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Status_Product.class)
                .buildSessionFactory();

        boolean isWork = true;
        while(isWork){
            System.out.println("Введите 1 для работы с материалами");
            System.out.println("Введите 2 для работы с операциям");
            System.out.println("Введите 3 для работы с заказами");
            System.out.println("Введите 4 для работы с изделиями");
            System.out.println("Введите 5 для работы со статусами");
            System.out.println("Введите 6 для выхода");

            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt();

            switch (i){
                case 1:
                    MaterialLogic materialLogic = new MaterialLogic();
                    materialLogic.work(sessionFactory);
                    break;
                case 2:
                    OperationLogic operationLogic = new OperationLogic();
                    operationLogic.work(sessionFactory);
                    break;
                case 3:
                    OrderLogic orderLogic = new OrderLogic();
                    orderLogic.work(sessionFactory);
                    break;
                case 4:
                    ProductLogic productLogic = new ProductLogic();
                    productLogic.work(sessionFactory);
                    break;
                case 5:
                    StatusProductLogic statusProductLogic = new StatusProductLogic();
                    statusProductLogic.work(sessionFactory);
                    break;
                case 6:
                    isWork = false;
                    break;
            }
        }
        sessionFactory.close();
    }
}