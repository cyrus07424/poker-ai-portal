package controllers;

import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.Controller;
import play.mvc.Http;
import services.EmailService;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * コントローラーのベースクラス.
 *
 * @author cyrus
 */
@Singleton
public abstract class AbstractController extends Controller {

    /**
     * フォームファクトリー.
     */
    @Inject
    protected FormFactory formFactory;

    /**
     * メッセージAPI.
     */
    @Inject
    protected MessagesApi messagesApi;

    /**
     * Eメールサービス.
     */
    @Inject
    protected EmailService emailService;

    /**
     * リファラを取得.
     *
     * @return
     */
    protected String getReferer() {
        return request().header(Http.HeaderNames.REFERER).orElse("");
    }
}