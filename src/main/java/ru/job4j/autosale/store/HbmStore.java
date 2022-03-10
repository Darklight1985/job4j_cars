package ru.job4j.autosale.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import ru.job4j.autosale.model.MarkAvto;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

public class HbmStore implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private HbmStore() {
    }

    private static final class Lazy {
        private static final Store INST = new HbmStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    /*
    public List<Item> findAllItemDay() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        Timestamp minusDay = Timestamp.valueOf(LocalDateTime.now().minusDays(1));
        return this.tx(session -> session.createQuery(
                "select distinct i from Ad i "
                        + "join fetch i.car c "
                        + "join fetch c.bodyType b "
                        + "join fetch c.markAvto m "
                        + "where i.created between :iNow and :iYesterday", Item.class
        ).setParameter("iNow", now).setParameter("iYesterday", minusDay).getResultList());
    }

    public List<AbstractReadWriteAccess.Item> findItemWithMark(MarkAvto markAvto) {
        return this.tx(session -> session.createQuery(
                "select distinct i from Ad i "
                        + "join fetch i.car c "
                        + "join fetch c.bodyType b "
                        + "join fetch c.markAvto m "
                        + "where m = :mark", Item.class
        ).setParameter("mark", markAvto).getResultList());
    }

    public List<Item> findItemWithPhoto() {

            return this.tx(session -> session.createQuery(
                 "select distinct i from Ad i "
                         + "join fetch i.car c "
                         + "join fetch c.bodyType b "
                         + "join fetch c.markAvto m "
                         + "where i.photo is not null", Item.class
         ).getResultList());
    }
*/
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
