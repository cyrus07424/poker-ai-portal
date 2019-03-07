package filters;

import akka.stream.Materializer;
import play.Logger;
import play.mvc.Filter;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

/**
 * ログフィルター.<br>
 * https://www.playframework.com/documentation/2.6.x/JavaHttpFilters#a-simple-logging-filter.
 *
 * @author cyrus
 */
public class LoggingFilter extends Filter {

    /**
     * コンストラクタ.
     *
     * @param mat
     */
    @Inject
    public LoggingFilter(Materializer mat) {
        super(mat);
    }

    @Override
    public CompletionStage<Result> apply(Function<Http.RequestHeader, CompletionStage<Result>> nextFilter, Http.RequestHeader requestHeader) {
        // 処理開始時間を取得
        long startTime = System.currentTimeMillis();

        // アクセス情報を出力
        Logger.info("URI:{} {}", requestHeader.method(), requestHeader.uri());
        Logger.info("Referer:{}", requestHeader.header(Http.HeaderNames.REFERER).orElse(null));
        Logger.info("User-Agent:{}", requestHeader.header(Http.HeaderNames.USER_AGENT).orElse(null));
        Logger.info("RemoteAddress:{}", requestHeader.remoteAddress());

        // 現在のセッション情報を出力
        Logger.info("Session:{}", requestHeader.asScala().session().data());

        return nextFilter.apply(requestHeader).thenApply(result -> {
            // 処理終了時間を取得
            long endTime = System.currentTimeMillis();
            long requestTime = endTime - startTime;

            Logger.info("{} {} took {}ms and returned {}",
                    requestHeader.method(), requestHeader.uri(), requestTime, result.status());
            return result.withHeader("Request-Time", "" + requestTime);
        });
    }
}