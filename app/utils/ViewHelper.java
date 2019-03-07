package utils;

import constants.Commons;
import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ビューヘルパー.
 *
 * @author cyrus
 */
public class ViewHelper {

    /**
     * 改行文字をBRタグに変換.
     *
     * @param text
     * @return
     */
    public static String breakToBr(String text) {
        return StringUtils.defaultString(text).replaceAll("\n", "<br>");
    }

    /**
     * 日付をフォーマット.
     *
     * @return
     */
    public static String formatDateYMDHMS(Object date) {
        if (date instanceof Date) {
            return new SimpleDateFormat(Commons.DATE_FORMAT_YMDHMS_WITH_SLASH).format(date);
        } else {
            return "";
        }
    }

    /**
     * 数値をフォーマット.
     *
     * @param number
     * @return
     */
    public static String formatNumber(Object number) {
        return NumberFormat.getNumberInstance().format(number);
    }
}