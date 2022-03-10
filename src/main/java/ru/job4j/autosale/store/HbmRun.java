package ru.job4j.autosale.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.autosale.model.*;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder().configure().build();

        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            BodyType sedan = BodyType.of("Седан");
            BodyType hatchbak = BodyType.of("Хэтчбэк");
            BodyType stationWagon = BodyType.of("Универсал");
            BodyType coupe = BodyType.of("Купе");
            BodyType pickup = BodyType.of("Пикап");

            MarkAvto kia = MarkAvto.of("Kia");
            MarkAvto renau = MarkAvto.of("Renau");
            MarkAvto lada = MarkAvto.of("Lada");

            Model kiaRio = Model.of("KiaRio");
            kia.addModel(kiaRio);

            EngineType gasoline = EngineType.of("Бензин");
            EngineType diesel = EngineType.of("Дизель");
            EngineType gas = EngineType.of("Газ");
            EngineType hybrid = EngineType.of("Гибридный");

            DriveType allWheel = DriveType.of("Полный");
            DriveType front = DriveType.of("Передний");
            DriveType rear = DriveType.of("Задний");

            Car car = Car.of("125", kiaRio, sedan, gasoline, front);
            User user = User.of("Dima", "123456");
            Ad adOne = Ad.of("Продаю машину", car, user, false);
            adOne.setUser(user);

            session.save(user);
            car = session.get(Car.class, 1);

            System.out.println(car);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
