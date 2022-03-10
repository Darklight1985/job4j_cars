package ru.job4j.autosale.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.autosale.model.User;

import java.util.function.Function;

public class HbmStoreUser implements StoreUser, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private HbmStoreUser() {
    }

    private static final class Lazy {
        private static final StoreUser INST = new HbmStoreUser();
    }

    public static StoreUser instOf() {
        return Lazy.INST;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Override
    public void addUser(User user) {
        this.tx(session -> session.save(user));
    }

    @Override
    public User findUser(String nameUser) {
        return (User) this.tx(session -> {
            final Query query = session.createQuery("from User where name = :value");
            query.setParameter("value", nameUser);
            return query.uniqueResult();
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
