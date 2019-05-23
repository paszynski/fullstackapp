package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Entry;
import models.Order;
import play.libs.Json;
import play.mvc.BodyParser;
import models.EntryRepository;
import models.OrderRepository;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Date;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import static play.libs.Json.toJson;

/**
 * The controller keeps all database operations behind the repository, and uses
 * {@link play.libs.concurrent.HttpExecutionContext} to provide access to the
 * {@link play.mvc.Http.Context} methods like {@code request()} and {@code flash()}.
 */
public class OrderController extends Controller {

    private final OrderRepository orderRepository;
    private final EntryRepository entryRepository;
    private final HttpExecutionContext ec;

    @Inject
    public OrderController(OrderRepository orderRepository, EntryRepository entryRepository, HttpExecutionContext ec) {
        this.orderRepository = orderRepository;
        this.entryRepository = entryRepository;
        this.ec = ec;
    }
/*
    public Result index(final Http.Request request) {
        return ok(views.html.index.render(request));
    }
*/
    @BodyParser.Of(BodyParser.Json.class)
    public CompletionStage<Result> addOrder(final Http.Request request) {
        JsonNode json = request.body().asJson();
        Order order = Json.fromJson(json, Order.class);
        Date date = new Date();
        order.setDateAdded(date);

        for (Entry entry : order.getEntries()){
            entry.setOrder(order);
        }

        return orderRepository
                .add(order)
                .thenApplyAsync(r -> ok()); //redirect(routes.PersonController.index()), ec.current());
    }

    public CompletionStage<Result> getOrders() {
        return orderRepository
                .list()
                .thenApplyAsync(personStream -> ok(toJson(personStream.collect(Collectors.toList()))), ec.current());
    }

}