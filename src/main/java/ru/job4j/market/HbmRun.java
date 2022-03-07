package ru.job4j.market;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.market.model.Car;
import ru.job4j.market.model.Driver;
import ru.job4j.market.model.Engine;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder().configure().build();

        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Engine engine123 = Engine.of("W123");
            session.save(engine123);

            Car rio  = Car.of("KiaRio", engine123);
            Car solyaris = Car.of("Solyaris", engine123);

            Driver driverOne = Driver.of("Vasya");
            Driver driverTwo = Driver.of("Misha");

            rio.addDriver(driverOne);
            solyaris.addDriver(driverTwo);

            session.persist(rio);
            session.persist(solyaris);

            session.getTransaction().commit();

            Car car = session.get(Car.class, 1);
            System.out.println(car);

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
