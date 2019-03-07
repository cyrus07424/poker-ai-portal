package services;

import constants.Configurations;
import models.ClientUser;
import play.Logger;
import play.i18n.MessagesApi;
import play.libs.mailer.Attachment;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import play.mvc.Controller;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;

/**
 * Eメールサービス.
 *
 * @author cyrus
 */
@Singleton
public class EmailService {

    /**
     * メールクライアント.
     */
    @Inject
    private MailerClient mailerClient;

    /**
     * メッセージAPI.
     */
    @Inject
    private MessagesApi messagesApi;

    /**
     * クライアントユーザー宛に仮登録完了メールを送信.
     *
     * @param clientUser
     */
    public void sendTemporaryRegisteredEmail(ClientUser clientUser, String rawPassword) {
        // TO一覧を作成
        List<String> toList = Collections.singletonList(clientUser.email);

        // 題名を作成
        String subject = messagesApi.get(Controller.lang(), "mail.temporaryRegistered.title");

        // 本文を作成
        String body = messagesApi.get(Controller.lang(), "mail.temporaryRegistered.body", rawPassword, clientUser.apiKey,
                controllers.routes.ClientUserController.verifyRegister(clientUser.verifyUuid).absoluteURL(Controller.request()));

        // 送信
        sendEmail(toList, subject, body, null);
    }

    /**
     * Eメールを送信.
     *
     * @param toList
     * @param subject
     * @param body
     * @param attachmentList
     */
    private void sendEmail(List<String> toList, String subject, String body, List<Attachment> attachmentList) {
        Logger.debug("sendEmail {},{},{}", toList, subject, body);
        Email email = new Email();

        // Fromを設定
        email.setFrom(Configurations.SYSTEM_EMAIL_ADDRESS);

        // TO一覧を設定
        if (toList != null && !toList.isEmpty()) {
            email.setTo(toList);
        }

        // 題名を設定
        email.setSubject(subject);

        // 本文を設定
        email.setBodyText(body);

        // 添付ファイルを設定
        if (attachmentList != null && !attachmentList.isEmpty()) {
            email.setAttachments(attachmentList);
        }

        // 送信
        mailerClient.send(email);
    }
}