package ru.job4j.autosale.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.autosale.model.Ad;

import java.util.List;
import java.util.function.Function;

public class HbmStoreAd implements StoreAd, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private HbmStoreAd() {
    }

    private static final class Lazy {
        private static final StoreAd INST = new HbmStoreAd();
    }

    public static StoreAd instOf() {
        return Lazy.INST;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Override
    public int addAd(Ad ad) {
         return this.tx(session -> {
             return (int) session.save(ad);
         });
    }

    @Override
    public boolean updateAd(Ad ad) {
        return this.tx(session ->  {
             session.saveOrUpdate(ad);
             return true;
         });
    }

    @Override
    public List<Ad> findAll() {
        return this.tx(session -> {
            final Query query = session.createQuery("select distinct i from Ad i "
                    + "join fetch i.user "
                    + "join fetch i.car c "
                    + "join fetch c.bodyType "
                    + "join fetch c.engine "
                    + "join fetch c.driveType "
                    + "join fetch c.model", Ad.class);
            List<Ad> result = (List<Ad>) query.getResultList();
            return result;
        });
    }

    @Override
    public Ad findAdById(int id) {
        return this.tx(session -> {
            final Query query = session.createQuery("select distinct i from Ad i "
                    + "join fetch i.user "
                    + "join fetch i.car c "
                    + "join fetch c.bodyType "
                    + "join fetch c.engine "
                    + "join fetch c.driveType "
                    + "join fetch c.model "
                    + "where i.id = :value", Ad.class);
            query.setParameter("value", id);
            Ad ad =  (Ad) query.uniqueResult();
            return ad;
        });
    }

    @Override
    public List<Ad> findNotFinal() {
        return this.tx(session -> {
            final Query query = session.createQuery("from Ad where done = :value");
            query.setParameter("value", false);
            List<Ad> result = (List<Ad>) query.getResultList();
            return result;
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
