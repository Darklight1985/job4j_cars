package ru.job4j.market;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.market.model.Car;
import ru.job4j.couples.model.Driver;
import ru.job4j.couples.model.Engine;
import ru.job4j.market.model.BodyType;
import ru.job4j.market.model.Item;
import ru.job4j.market.model.MarkAvto;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder().configure().build();

        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            BodyType bodyType = BodyType.of("sedan");
            MarkAvto markAvto = MarkAvto.of("KiaRio");
            Car car = Car.of(markAvto, bodyType);
            Item item = Item.of("Sale avto");

            item.setCar(car);
            session.save(bodyType);
            session.save(markAvto);
            session.save(car);
            session.save(item);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
