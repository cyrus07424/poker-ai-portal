package constants;

import play.Configuration;

/**
 * 環境設定.
 *
 * @author cyrus
 */
public interface Configurations {

    /**
     * アプリケーション名.
     */
    String APPLICATION_NAME = Configuration.root().getString("application.name");

    /**
     * システムの送信専用メールアドレス.
     */
    String SYSTEM_EMAIL_ADDRESS = Configuration.root().getString("play.mailer.user");
}