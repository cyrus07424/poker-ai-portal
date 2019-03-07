package utils;

import models.ClientUser;
import org.mindrot.jbcrypt.BCrypt;

/**
 * BCryptヘルパー.
 *
 * @author cyrus
 */
public class BCryptHelper {

    /**
     * パスワードのハッシュを取得.
     *
     * @param password
     * @return
     */
    public static String getHashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * クライアントユーザーのパスワードを確認.
     *
     * @param clientUser
     * @param password
     * @return パスワードが一致する場合はtrue、そうでない場合はfalse
     */
    public static boolean checkUserPassword(ClientUser clientUser, String password) {
        if (clientUser != null) {
            return BCrypt.checkpw(password, clientUser.password);
        }
        return false;
    }
}