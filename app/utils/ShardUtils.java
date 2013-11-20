package utils;

import java.util.Calendar;

/**
 * Sharding utilities.
 * 
 * @author Thanh.Nguyen <btnguyen2k@gmail.com>
 */
public class ShardUtils {

    /**
     * Computes weekly shard id.
     * 
     * @param delta
     *            plus/minus this number of weeks from the current week_of_year
     * @return
     */
    public static String shardWeekly(int delta) {
        Calendar cal = DateTimeUtils.getCalendar();
        if (delta != 0) {
            cal.add(Calendar.WEEK_OF_YEAR, delta);
        }
        int year = cal.get(Calendar.YEAR);
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        String shard = "" + year + (week < 10 ? "0" + week : week);
        return shard;
    }

    /**
     * Computes weekly shard id for the current week..
     * 
     * @return
     */
    public static String shardWeekly() {
        return shardWeekly(0);
    }

    /**
     * Computes monthly shard id.
     * 
     * @param delta
     *            plus/minus this number of weeks from the current month
     * @return
     */
    public static String shardMonthly(int delta) {
        Calendar cal = DateTimeUtils.getCalendar();
        if (delta != 0) {
            cal.add(Calendar.MONTH, delta);
        }
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        String shard = "" + year + (month < 10 ? "0" + month : month);
        return shard;
    }

    /**
     * Computes monthly shard id for the current week..
     * 
     * @return
     */
    public static String shardMonthly() {
        return shardMonthly(0);
    }

    /**
     * Computes weekly shard id.
     * 
     * @param delta
     *            plus/minus this number of weeks from the current week_of_year
     * @return
     */
    public static String shardWeeklyUTC(int delta) {
        Calendar cal = DateTimeUtils.getCalendarUTC();
        if (delta != 0) {
            cal.add(Calendar.WEEK_OF_YEAR, delta);
        }
        int year = cal.get(Calendar.YEAR);
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        String shard = "" + year + (week < 10 ? "0" + week : week);
        return shard;
    }

    /**
     * Computes weekly shard id for the current week..
     * 
     * @return
     */
    public static String shardWeeklyUTC() {
        return shardWeeklyUTC(0);
    }

    /**
     * Computes monthly shard id.
     * 
     * @param delta
     *            plus/minus this number of weeks from the current month
     * @return
     */
    public static String shardMonthlyUTC(int delta) {
        Calendar cal = DateTimeUtils.getCalendarUTC();
        if (delta != 0) {
            cal.add(Calendar.MONTH, delta);
        }
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        String shard = "" + year + (month < 10 ? "0" + month : month);
        return shard;
    }

    /**
     * Computes monthly shard id for the current week..
     * 
     * @return
     */
    public static String shardMonthlyUTC() {
        return shardMonthlyUTC(0);
    }
}
