package enums;

import io.ebean.annotation.EnumValue;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * クライアントユーザー状態.
 *
 * @author cyrus
 */
public enum ClientUserStatus {

    /**
     * メールアドレス確認中.
     */
    @EnumValue("temporary")
    TEMPORARY("メールアドレス確認中"),

    /**
     * メールアドレス確認済.
     */
    @EnumValue("verified")
    VERIFIED("メールアドレス確認済"),

    /**
     * 退会済.
     */
    @EnumValue("leaved")
    LEAVED("退会済");

    /**
     * 値の名前.
     */
    private String value;

    /**
     * コンストラクタ.
     *
     * @param value
     */
    ClientUserStatus(String value) {
        this.value = value;
    }

    /**
     * 値を取得.
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * optionエレメント用のMapを取得.
     *
     * @return
     */
    public static Map<String, String> options() {
        Map<String, String> options = new LinkedHashMap<>();
        for (ClientUserStatus option : values()) {
            options.put(option.name(), option.value);
        }
        return options;
    }
}