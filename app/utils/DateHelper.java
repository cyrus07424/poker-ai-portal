package utils;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * 日付時刻ヘルパー.
 *
 * @author cyrus
 */
public class DateHelper {

    /**
     * 現在の日付を取得.
     *
     * @return
     */
    public static Date getTruncatedTodayDate() {
        return DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
    }
}