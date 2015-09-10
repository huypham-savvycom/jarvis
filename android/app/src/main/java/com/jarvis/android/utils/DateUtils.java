package com.jarvis.android.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Date util use in application
 */
public class DateUtils {

    public static final String MERCHANT_PHOTO_DATE = "h:mmaa M.d.yyyy";
    public static final String COMMENT_PHOTO_DATE = "M/d/yyyy";

    public static final SimpleDateFormat ISO8601_PARSER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
    public static final SimpleDateFormat ISO8601_PARSER_WITH_MS = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault());

    /**
     * Convert ISO8601-compliant date time string to milliseconds.
     *
     * @param dateTime
     * @return long
     */
    public static long parseDateTimeString(String dateTime) {
        if (!TextUtils.isEmpty(dateTime)) {
            String s = dateTime.replace("Z", "+00:00");
            if (!s.contains("+")) {
                s = s + "+00:00";
            }
            Exception e;
            try {
                return ISO8601_PARSER.parse(s).getTime();
            } catch (ParseException ignored) {
                e = ignored;
                try {
                    return ISO8601_PARSER_WITH_MS.parse(s).getTime();
                } catch (ParseException ignoredAgain) {
                    e = ignoredAgain;
                }
            }
            if (e != null) {
                e.printStackTrace();
            }
        }
        return 0L;
    }

    /**
     * Convert Unix date to String
     * @param date
     * @param format
     * @return String
     */
    public static String getStringTime(long date, String format) {
        SimpleDateFormat formater = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);
        return formater.format(cal.getTime());
    }

    /**
     * Get current date Unix
     * @return long
     */
    public static long getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        return cal.getTimeInMillis();
    }

    /**
     * Get current date as a String
     * @return
     */
    public static String getCurrentDateString() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getCurrentDate());
        return ISO8601_PARSER_WITH_MS.format(cal.getTime());
    }

    /**
     * Convert date Unix to String for Message screen
     * @param date
     * @return String
     */
    public static String getStringTimeMessage(long date) {
        SimpleDateFormat formater = null;

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);

        Calendar calToday = Calendar.getInstance();

        int dayOfYears = cal.get(Calendar.DAY_OF_YEAR);
        int todayOfYears = calToday.get(Calendar.DAY_OF_YEAR);

        boolean sameDay = cal.get(Calendar.YEAR) == calToday.get(Calendar.YEAR) &&
                dayOfYears == todayOfYears;

        boolean yesterday = cal.get(Calendar.YEAR) == calToday.get(Calendar.YEAR) &&
                (dayOfYears+1) == todayOfYears;

        int dayOfWeeks = cal.get(Calendar.DAY_OF_YEAR);
        int todayOfWeeks = calToday.get(Calendar.DAY_OF_YEAR);
        boolean inWeeks = cal.get(Calendar.YEAR) == calToday.get(Calendar.YEAR) &&
                (todayOfYears - dayOfYears >= 0) && (todayOfYears - dayOfYears <= 7) &&
                (todayOfWeeks - dayOfWeeks >= 0) && (todayOfWeeks - dayOfWeeks <= 7);
        String withMoment = "";
        withMoment = new SimpleDateFormat("MMM dd").format(cal.getTime()) + " at ";
        if (sameDay) {
            withMoment = "Today at ";
        } else if (yesterday) {
            withMoment = "Yesterday at ";
        } else if (inWeeks) {
            withMoment = new SimpleDateFormat("EEE").format(cal.getTime()) + " at ";
        }

        return withMoment + new SimpleDateFormat("hh:mm a").format(cal.getTime());
    }
    /**
     * Convert date Unix to String for Notification screen
     * @param date
     * @return String
     */
    public static String getStringTimeNotification(long date) {
        SimpleDateFormat formater = null;

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);

        Calendar calToday = Calendar.getInstance();

        int dayOfYears = cal.get(Calendar.DAY_OF_YEAR);
        int todayOfYears = calToday.get(Calendar.DAY_OF_YEAR);

        boolean sameDay = cal.get(Calendar.YEAR) == calToday.get(Calendar.YEAR) &&
                dayOfYears == todayOfYears;

        boolean yesterday = cal.get(Calendar.YEAR) == calToday.get(Calendar.YEAR) &&
                (dayOfYears+1) == todayOfYears;

        int dayOfWeeks = cal.get(Calendar.DAY_OF_YEAR);
        int todayOfWeeks = calToday.get(Calendar.DAY_OF_YEAR);
        boolean inWeeks = cal.get(Calendar.YEAR) == calToday.get(Calendar.YEAR) &&
                (todayOfYears - dayOfYears >= 0) && (todayOfYears - dayOfYears <= 7) &&
                (todayOfWeeks - dayOfWeeks >= 0) && (todayOfWeeks - dayOfWeeks <= 7);
        String withMoment = "";
        withMoment = new SimpleDateFormat("MMM dd").format(cal.getTime()) + " at ";
        if (sameDay) {
            withMoment = "Today at ";
        } else if (yesterday) {
            withMoment = "Yesterday at ";
        } else if (inWeeks) {
            withMoment = new SimpleDateFormat("EEE").format(cal.getTime()) + " at ";
        }

        return withMoment + new SimpleDateFormat("hh:mm a").format(cal.getTime());
    }



    public static int getDayOfWeek() {
        Calendar c = Calendar.getInstance();
        c.setTime(c.getTime());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek;
    }

    public static String getDayString(int day) {
        String newDay = null;
        switch (day) {
            case 0:
                newDay = "Sunday";
                break;
            case 1:
                newDay = "Monday";
                break;
            case 2:
                newDay = "Tuesday";
                break;
            case 3:
                newDay = "Wednesday";
                break;
            case 4:
                newDay = "Thursday";
                break;
            case 5:
                newDay = "Friday";
                break;
            case 6:
                newDay = "Saturday";
                break;
            default:
                newDay = "Undefine";
                break;
        }
        return newDay;
    }


    public static String formatTime(String time) {
        String newTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        try {
            newTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(sdf.parse(time));
        } catch (ParseException e) {
        }
        return newTime;
    }


    public static String getBulletinBoardDate(long date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);

        Calendar calToday = Calendar.getInstance();

        int dayOfYears = cal.get(Calendar.DAY_OF_YEAR);
        int todayOfYears = calToday.get(Calendar.DAY_OF_YEAR);
        boolean sameDay = cal.get(Calendar.YEAR) == calToday.get(Calendar.YEAR) &&
                dayOfYears == todayOfYears;
        String withMoment = "";
        if (sameDay) {
            withMoment = "Today";
        } else {
            withMoment = new SimpleDateFormat("MM/dd").format(cal.getTime());
        }

        return withMoment + "\n" + new SimpleDateFormat("hh:mm a").format(cal.getTime());
    }
}
