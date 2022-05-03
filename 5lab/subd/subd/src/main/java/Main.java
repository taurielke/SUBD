import logic.*;
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
            System.out.println("Insert 1 to work with materials");
            System.out.println("Insert 2 to work with operations");
            System.out.println("Insert 3 to work with orders");
            System.out.println("Insert 4 to work with products");
            System.out.println("Insert 5 to work with statuses");
            System.out.println("Insert 6 to exit");
            System.out.println("Insert 7 to see the main request");

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
                case 7:
                    MainSQLRequestLogic mainSQLRequestLogic = new MainSQLRequestLogic();
                    mainSQLRequestLogic.work(sessionFactory);
                    break;
            }
        }
        sessionFactory.close();
    }
}