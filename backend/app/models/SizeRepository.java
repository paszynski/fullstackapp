package models;

import com.google.inject.ImplementedBy;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(JPASizeRepository.class)
public interface SizeRepository {
    CompletionStage<Stream<Size>> list();
}
