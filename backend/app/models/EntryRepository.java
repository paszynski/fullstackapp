package models;

import com.google.inject.ImplementedBy;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(JPAEntryRepository.class)
public interface EntryRepository {
    CompletionStage<Stream<Entry>> list();

    CompletionStage<Entry> add(Entry entry);
}

