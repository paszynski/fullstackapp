package models;

import com.google.inject.ImplementedBy;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(JPAOrderRepository.class)
public interface OrderRepository {
    CompletionStage<Stream<Order>> list();
    CompletionStage<Order> add(Order order);
}

