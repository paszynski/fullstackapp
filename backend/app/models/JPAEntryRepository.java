package models;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * Provide JPA operations running inside of a thread pool sized to the connection pool
 */
public class JPAEntryRepository implements EntryRepository {

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public JPAEntryRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Entry> add(Entry entry) {
        return supplyAsync(() -> wrap(em -> insert(em, entry)), executionContext);
    }

    @Override
    public CompletionStage<Stream<Entry>> list() {
        return supplyAsync(() -> wrap(em -> list(em)), executionContext);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Entry insert(EntityManager em, Entry entry) {
        em.persist(entry);
        return entry;
    }

    private Stream<Entry> list(EntityManager em) {
        List<Entry> entries = em.createQuery("select o from models.Entry o", Entry.class).getResultList();
        return entries.stream();
    }
}