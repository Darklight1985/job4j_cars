package ru.job4j.autosale.store;

import  org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.autosale.model.*;

import java.util.List;
import java.util.function.Function;

public class HbmStoreCar implements StoreCar, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private HbmStoreCar() {
    }

    private static final class Lazy {
        private static final StoreCar INST = new HbmStoreCar();
    }

    public static StoreCar instOf() {
        return Lazy.INST;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Override
    public void addCar(Car car) {
        this.tx(session -> session.save(car));
    }

    @Override
    public Car createCar(String power, String modelId, String bodyId,
                          String engineId, String driveID) {
       return this.tx(session -> {
            Car car = Car.of(power, session.find(Model.class, Integer.parseInt(modelId)),
                    session.find(BodyType.class, Integer.parseInt(bodyId)),
                    session.find(EngineType.class, Integer.parseInt(engineId)),
                    session.find(DriveType.class, Integer.parseInt(driveID)));
            session.save(car);
            return car;
    });
    }

    @Override
    public Car findCar(int id) {
        return (Car) this.tx(session -> {
            final Query query = session.createQuery("from Car where id = :value");
            query.setParameter("value", id);
            return query.uniqueResult();
        });
    }

    @Override
    public List<Model> findModel() {
        return this.tx(session -> {
            final Query query = session.createQuery("from Model");
            return query.getResultList();
        });
    }

    @Override
    public List<BodyType> findBody() {
        return this.tx(session -> {
            final Query query = session.createQuery("from BodyType");
            return query.getResultList();
        });
    }

    @Override
    public List<DriveType> findDrive() {
        return this.tx(session -> {
            final Query query = session.createQuery("from DriveType");
            return query.getResultList();
        });
    }

    @Override
    public List<EngineType> findEngine() {
        return this.tx(session -> {
            final Query query = session.createQuery("from EngineType");
            return query.getResultList();
        });
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

}
