package models;

import com.google.inject.ImplementedBy;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(JPAColorSizeLimitRepository.class)
public interface ColorSizeLimitRepository {

    CompletionStage<ColorSizeLimit> decrementLimit(ColorSizeLimitIdentity colorSizeLimitIdentity);
    CompletionStage<Stream<ColorSizeLimit>> list();
}
