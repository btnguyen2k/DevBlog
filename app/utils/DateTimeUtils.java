package utils;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Date & time utilities.
 * 
 * @author Thanh.Nguyen <btnguyen2k@gmail.com>
 */
public class DateTimeUtils {

    /**
     * Gets the {@link Calendar} instance for the default timezone.
     * 
     * @return
     */
    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    /**
     * Gets the UTC {@link Calendar} instance.
     * 
     * @return
     */
    public static Calendar getCalendarUTC() {
        return Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    }
}
