package constants;

/**
 * メッセージ取得用のキー.
 *
 * @author cyrus
 */
public interface MessageKeys {

    /**
     * 作成成功.
     */
    String INFO_CREATE_SUCCESS = "info.createSuccess";

    /**
     * 更新成功.
     */
    String INFO_UPDATE_SUCCESS = "info.updateSuccess";

    /**
     * 削除成功.
     */
    String INFO_DELETE_SUCCESS = "info.deleteSuccess";

    /**
     * ログアウト成功.
     */
    String INFO_LOGOUT_SUCCESS = "info.logoutSuccess";

    /**
     * 共通エラー.
     */
    String ERROR_DEFAULT = "error.default";

    /**
     * 作成失敗.
     */
    String ERROR_CREATE_FAILED = "error.createFailed";

    /**
     * 更新失敗.
     */
    String ERROR_UPDATE_FAILED = "error.updateFailed";

    /**
     * 削除失敗.
     */
    String ERROR_DELETE_FAILED = "error.deleteFailed";

    /**
     * ログイン失敗.
     */
    String ERROR_LOGIN_FAILED = "error.loginFailed";

    /**
     * 処理失敗.
     */
    String ERROR_PROCESS_FAILED = "error.processFailed";

    /**
     * 利用規約の同意にチェックがない.
     */
    String ERROR_AGREE_NOT_CHECKED = "error.agreeNotChecked";

    /**
     * メールアドレス重複.
     */
    String ERROR_DUPLICATE_EMAIL = "error.duplicateEmail";

    /**
     * パスワードが一致しない.
     */
    String ERROR_PASSWORD_NOT_MATCHED = "error.passwordNotMatched";

    /**
     * 不正なURL.
     */
    String ERROR_INVALID_URL = "error.invalidUrl";

    /**
     * 不正なパスワードの形式.
     */
    String ERROR_INVALID_PASSWORD_PATTERN = "error.invalidPasswordPattern";
}