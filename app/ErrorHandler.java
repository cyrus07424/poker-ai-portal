import com.typesafe.config.Config;
import constants.Commons;
import org.apache.commons.lang3.StringUtils;
import play.Environment;
import play.api.OptionalSourceMapper;
import play.api.routing.Router;
import play.http.DefaultHttpErrorHandler;
import play.libs.Json;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * エラーハンドラー.
 *
 * @author cyrus
 */
@Singleton
public class ErrorHandler extends DefaultHttpErrorHandler {

    /**
     * コンストラクタ.
     *
     * @param config
     * @param environment
     * @param sourceMapper
     * @param routes
     */
    @Inject
    public ErrorHandler(Config config, Environment environment, OptionalSourceMapper sourceMapper, Provider<Router> routes) {
        super(config, environment, sourceMapper, routes);
    }

    /**
     * クライアントエラー.
     *
     * @param request
     * @param statusCode
     * @param message
     * @return
     */
    @Override
    public CompletionStage<Result> onClientError(RequestHeader request, int statusCode, String message) {
        return super.onClientError(request, statusCode, message);
    }

    /**
     * サーバーエラー.
     *
     * @param request
     * @param exception
     * @return
     */
    @Override
    public CompletionStage<Result> onServerError(RequestHeader request, Throwable exception) {
        if (request.contentType().isPresent() && StringUtils.equals(request.contentType().get(), Commons.MIME_TYPE_APPLICATION_JSON)) {
            Map<String, String> map = new HashMap<>();
            map.put("cause", exception.getCause().toString());
            map.put("message", exception.getMessage());
            return CompletableFuture.completedFuture(Results.internalServerError(Json.toJson(map)));
        } else {
            return super.onServerError(request, exception);
        }
    }
}