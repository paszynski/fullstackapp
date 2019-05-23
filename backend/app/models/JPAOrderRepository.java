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
public class JPAOrderRepository implements OrderRepository {

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public JPAOrderRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Order> add(Order order) {
        return supplyAsync(() -> wrap(em -> insert(em, order)), executionContext);
    }

    @Override
    public CompletionStage<Stream<Order>> list() {
        return supplyAsync(() -> wrap(em -> list(em)), executionContext);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Order insert(EntityManager em, Order order) {
        em.persist(order);
        return order;
    }

    private Stream<Order> list(EntityManager em) {
        List<Order> orders = em.createQuery("select o from models.Order o", Order.class).getResultList();
        return orders.stream();
    }
}