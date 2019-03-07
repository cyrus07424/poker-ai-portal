package models;

import constants.Commons;
import enums.ClientUserStatus;
import io.ebean.Finder;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

/**
 * クライアントユーザー.
 *
 * @author cyrus
 */
@Entity
public class ClientUser extends AbstractModel {

    /**
     * メールアドレス.
     */
    @Constraints.MaxLength(Commons.TEXT_MAX_LENGTH)
    @Constraints.Email
    @Constraints.Required
    @Column(nullable = false, unique = true)
    public String email;

    /**
     * パスワード.
     */
    @Column(nullable = false)
    public String password;

    /**
     * APIキー.
     */
    @Column(nullable = false)
    public String apiKey;

    /**
     * クライアントユーザー状態.
     */
    @Constraints.Required
    @Column(nullable = false)
    public ClientUserStatus clientUserStatus;

    /**
     * 本登録日時.
     */
    @Formats.DateTime(pattern = Commons.DATE_FORMAT_YMDHMS_WITH_SLASH)
    public Date verifyDate;

    /**
     * メールアドレス変更中フラグ.
     */
    @Column(nullable = false)
    public Boolean emailUpdateFlag = false;

    /**
     * 新しいメールアドレス(メールアドレス変更用).
     */
    @Constraints.MaxLength(Commons.TEXT_MAX_LENGTH)
    @Constraints.Email
    public String newEmail;

    /**
     * 本登録用UUID.
     */
    @Constraints.MaxLength(Commons.TEXT_MAX_LENGTH)
    public String verifyUuid;

    /**
     * メールアドレス変更用UUID.
     */
    @Constraints.MaxLength(Commons.TEXT_MAX_LENGTH)
    public String emailUpdateUuid;

    @Override
    public List<ValidationError> validate() {
        return null;
    }

    /**
     * Finder.
     */
    public static Finder<Long, ClientUser> finder = new Finder<>(ClientUser.class);

    /**
     * IDから1件取得.
     *
     * @param id
     * @return
     */
    public static ClientUser findById(Long id) {
        return finder.byId(id);
    }

    /**
     * メールアドレスから1件取得.
     *
     * @param email
     * @return
     */
    public static ClientUser findByEmail(String email) {
        return finder.query().where().eq("email", email).findOne();
    }

    /**
     * 本登録用UUIDから1件取得.
     *
     * @param verifyUuid
     * @return
     */
    public static ClientUser findByVerifyUuid(String verifyUuid) {
        // 仮登録ユーザーを対象
        return finder.query().where().eq("clientClientUserStatus", ClientUserStatus.TEMPORARY).eq("verifyUuid", verifyUuid).findOne();
    }

    /**
     * メールアドレス変更用UUIDからメールアドレス変更中のユーザーを1件取得.
     *
     * @param emailUpdateUuid
     * @return
     */
    public static ClientUser findByEmailUpdateUuid(String emailUpdateUuid) {
        return finder.query().where().eq("emailUpdateFlag", true).eq("emailUpdateUuid", emailUpdateUuid).findOne();
    }

    /**
     * メールアドレス変更用UUIDとパスワードからメールアドレス変更中のユーザーを1件取得.
     *
     * @param uuid
     * @param password
     * @return
     */
    public static ClientUser findByEmailUpdateUuidAndPassword(String uuid, String password) {
        return finder.query().where().eq("emailUpdateFlag", true).eq("emailUpdateUuid", uuid).eq("password", password).findOne();
    }
}