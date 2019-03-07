package utils;

import constants.Commons;
import constants.MessageKeys;
import enums.ClientUserStatus;
import models.ClientUser;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * フォーム用クラス.
 *
 * @author cyrus
 */
public class Forms {

    /**
     * 仮登録用フォーム.
     *
     * @author cyrus
     */
    @Constraints.Validate
    public static class TemporaryRegister implements Constraints.Validatable<List<ValidationError>> {

        /**
         * メールアドレス.
         */
        @Constraints.MaxLength(Commons.TEXT_MAX_LENGTH)
        @Constraints.Email
        @Constraints.Required
        public String email;

        /**
         * 利用規約に同意.<br>
         * (値が入っていれば許可).
         */
        @Constraints.Required(message = MessageKeys.ERROR_AGREE_NOT_CHECKED)
        public Boolean agree;

        @Override
        public List<ValidationError> validate() {
            List<ValidationError> errors = new ArrayList<>();

            // メールアドレス重複チェック
            ClientUser tempEntity = ClientUser.findByEmail(email);
            if ((tempEntity != null && tempEntity.clientUserStatus == ClientUserStatus.VERIFIED)) {
                // 本登録済みのクライアントユーザーのメールアドレスと入力したメールアドレスが重複している場合
                errors.add(new ValidationError("email", MessageKeys.ERROR_DUPLICATE_EMAIL));
            }

            return errors.isEmpty() ? null : errors;
        }
    }
}