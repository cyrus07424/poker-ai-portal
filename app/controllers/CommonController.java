package controllers;

import play.mvc.Result;
import views.html.common.index;
import views.html.common.privacyPolicy;
import views.html.common.termsOfUse;

import javax.inject.Singleton;

/**
 * 共通コントローラー.
 *
 * @author cyrus
 */
@Singleton
public class CommonController extends AbstractController {

    /**
     * トップ画面へリダイレクト.
     *
     * @return
     */
    public static Result goIndex() {
        return redirect(routes.CommonController.index());
    }

    /**
     * トップ画面表示.
     *
     * @return
     */
    public Result index() {
        return ok(index.render());
    }

    /**
     * 利用規約画面表示.
     *
     * @return
     */
    public Result termsOfUse() {
        return ok(termsOfUse.render());
    }

    /**
     * プライバシーポリシー画面表示.
     *
     * @return
     */
    public Result privacyPolicy() {
        return ok(privacyPolicy.render());
    }
}