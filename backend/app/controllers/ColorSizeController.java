package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import play.libs.Json;
import play.mvc.BodyParser;
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
public class ColorSizeController extends Controller {

    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final HttpExecutionContext ec;

    @Inject
    public ColorSizeController(ColorRepository colorRepository, SizeRepository sizeRepository, HttpExecutionContext ec) {
        this.colorRepository = colorRepository;
        this.sizeRepository = sizeRepository;
        this.ec = ec;
    }
    /*
        public Result index(final Http.Request request) {
            return ok(views.html.index.render(request));
        }
    */

    public CompletionStage<Result> getColors() {
        return colorRepository
                .list()
                .thenApplyAsync(personStream -> ok(toJson(personStream.collect(Collectors.toList()))), ec.current());
    }

    public CompletionStage<Result> getSizes() {
        return sizeRepository
                .list()
                .thenApplyAsync(personStream -> ok(toJson(personStream.collect(Collectors.toList()))), ec.current());
    }

}