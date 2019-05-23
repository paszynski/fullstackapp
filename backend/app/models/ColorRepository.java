package models;

import com.google.inject.ImplementedBy;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(JPAColorRepository.class)
public interface ColorRepository {
    CompletionStage<Stream<Color>> list();
}
