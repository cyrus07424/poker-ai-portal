package constants;

/**
 * 共通の定数.
 *
 * @author cyrus
 */
public interface Commons {

    /**
     * optionエレメントの初期ラベル.
     */
    String SELECT_LABEL_DEFAULT = "選択してください。";

    /**
     * 日付のフォーマット.
     */
    String DATE_FORMAT_YMDHMS_WITH_SLASH = "yyyy/MM/dd HH:mm:ss";

    /**
     * MIMEタイプ(application/json).
     */
    String MIME_TYPE_APPLICATION_JSON = "application/json";

    /**
     * 入力可能なパターン(半角英数字).
     */
    String PATTERN_ALPHANUMERIC = "[a-zA-Z0-9]+";

    /**
     * 入力可能なパターン(URL).
     */
    String PATTERN_URL = "https?://[\\w/:%#\\$&\\?\\(\\)~\\.=\\+\\-]+";

    /**
     * テキスト入力欄の最大文字数.
     */
    int TEXT_MAX_LENGTH = 255;

    /**
     * 入力可能なパスワードのパターン.
     */
    String PASSWORD_PATTERN = "[a-zA-Z0-9!-/:-@\\[-`{-\\~]+";

    /**
     * 一時ファイル名接頭辞.
     */
    String TEMPORARY_FILE_PREFIX = "app-";

    /**
     * 一時ファイル名接尾辞.
     */
    String TEMPORARY_FILE_SUFFIX = "temp";
}