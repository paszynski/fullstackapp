package models;

import play.Logger;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class JPAColorSizeLimitRepository implements ColorSizeLimitRepository {

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;

    final Logger.ALogger logger = Logger.of("colorsizelimitrepository");

    @Inject
    public JPAColorSizeLimitRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<ColorSizeLimit> decrementLimit(ColorSizeLimitIdentity cslId) {
        return supplyAsync(() -> wrap(em -> update(em, cslId)), executionContext);
    }

    @Override
    public CompletionStage<Stream<ColorSizeLimit>> list() {
        return supplyAsync(() -> wrap(em -> list(em)), executionContext);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private ColorSizeLimit insert(EntityManager em, ColorSizeLimit order) {
        em.persist(order);
        return order;
    }

    private ColorSizeLimit update(EntityManager em, ColorSizeLimitIdentity colorSizeLimitIdentity) {
        ColorSizeLimit csl = em.find(ColorSizeLimit.class , colorSizeLimitIdentity);

        logger.debug("jest wartość: " + csl.getLimit());
        //csl.setLimit(csl.getLimit()-1);
        csl.decrementLimit();
        logger.debug("zmieniona na: " + csl.getLimit());

        return csl;
    }

    private Stream<ColorSizeLimit> list(EntityManager em) {
        List<ColorSizeLimit> csls = em.createQuery("select csl from models.ColorSizeLimit csl where csl.limit > 0").getResultList();
        return csls.stream();
    }
}
