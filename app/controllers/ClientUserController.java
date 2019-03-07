package controllers;

import constants.FlashKeys;
import constants.MessageKeys;
import enums.ClientUserStatus;
import io.ebean.Ebean;
import models.ClientUser;
import play.Logger;
import play.data.Form;
import play.mvc.Result;
import utils.BCryptHelper;
import utils.Forms;
import utils.GenerateHelper;
import views.html.clientUser.add;
import views.html.clientUser.create;

import javax.inject.Singleton;
import java.util.Date;

/**
 * クライアントユーザーコントローラー.
 *
 * @author cyrus
 */
@Singleton
public class ClientUserController extends AbstractController {

    /**
     * 仮登録画面表示.
     *
     * @return
     */
    public Result add() {
        Form<Forms.TemporaryRegister> inputForm = formFactory.form(Forms.TemporaryRegister.class);

        // 初期値を設定
        Forms.TemporaryRegister inputData = new Forms.TemporaryRegister();

        inputForm = inputForm.fill(inputData);
        return ok(add.render(inputForm));
    }

    /**
     * 仮登録.
     *
     * @return
     */
    public Result create() {
        // フォームを取得
        Form<Forms.TemporaryRegister> inputForm = formFactory.form(Forms.TemporaryRegister.class).bindFromRequest();
        if (inputForm.hasErrors()) {
            return badRequest(add.render(inputForm));
        }

        // 入力データを取得
        Forms.TemporaryRegister inputData = inputForm.get();

        Ebean.beginTransaction();
        ClientUser entity;
        try {
            // データベースから検索
            ClientUser tempEntity = ClientUser.findByEmail(inputData.email);
            if (tempEntity != null) {
                // データが存在する場合
                entity = tempEntity;
            } else {
                // データが存在しない場合
                // 仮登録ユーザーを作成
                entity = new ClientUser();
                entity.email = inputData.email;
            }
            entity.clientUserStatus = ClientUserStatus.TEMPORARY;
            String password = GenerateHelper.generateNewPassword();
            entity.password = BCryptHelper.getHashedPassword(password);
            entity.apiKey = GenerateHelper.generateNewApiKey();

            // UUIDを設定
            entity.verifyUuid = GenerateHelper.generateNewUUID();

            if (entity.id == null) {
                // データを登録
                entity.save();
            } else {
                // データを更新
                entity.update();
            }

            // 仮登録完了メールを送信
            emailService.sendTemporaryRegisteredEmail(entity, password);

            Ebean.commitTransaction();
        } catch (Exception e) {
            Logger.error("error", e);

            Ebean.rollbackTransaction();

            inputForm = inputForm.withGlobalError(MessageKeys.ERROR_PROCESS_FAILED);
            return badRequest(add.render(inputForm));
        } finally {
            Ebean.endTransaction();
        }

        // 仮登録完了画面を表示
        return ok(create.render(entity));
    }

    /**
     * 本登録.
     *
     * @param uuid
     * @return
     */
    public Result verifyRegister(String uuid) {
        // 本登録用UUIDからユーザーを取得
        ClientUser entity = ClientUser.findByVerifyUuid(uuid);

        // ユーザーが取得できない場合
        if (entity == null) {
            //  トップ画面へリダイレクト
            flash(FlashKeys.ERROR, MessageKeys.ERROR_INVALID_URL);
            return CommonController.goIndex();
        }

        Ebean.beginTransaction();
        try {
            // 本登録に変更
            entity.clientUserStatus = ClientUserStatus.VERIFIED;
            entity.verifyDate = new Date();
            entity.verifyUuid = null;
            entity.update();

            Ebean.commitTransaction();
        } catch (Exception e) {
            Logger.error("error", e);

            Ebean.rollbackTransaction();

            //  トップ画面へリダイレクト
            flash(FlashKeys.ERROR, MessageKeys.ERROR_PROCESS_FAILED);
            return CommonController.goIndex();
        } finally {
            Ebean.endTransaction();
        }

        //  トップ画面へリダイレクト
        // FIXME
        flash(FlashKeys.SUCCESS, MessageKeys.INFO_UPDATE_SUCCESS);
        return CommonController.goIndex();
    }
}