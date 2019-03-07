package utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

/**
 * 生成ヘルパー.
 *
 * @author cyrus
 */
public class GenerateHelper {

    /**
     * UUIDを生成.
     *
     * @return
     */
    public static String generateNewUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * パスワードを生成.
     *
     * @return
     */
    public static String generateNewPassword() {
        return RandomStringUtils.random(10);
    }

    /**
     * APIキーを生成.
     *
     * @return
     */
    public static String generateNewApiKey() {
        return RandomStringUtils.random(100);
    }
}