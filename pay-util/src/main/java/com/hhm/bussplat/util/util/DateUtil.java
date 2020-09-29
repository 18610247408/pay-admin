package com.hhm.bussplat.util.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间处理类
 * @author q
 * @time 2020/5/16 3:41 下午
 */
public class DateUtil {
    public static final String YYYY_MM_DD = "yyyy-MM-dd";//默认
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYY_MM = "yyyy-MM";

    private DateUtil() {
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null)
            throw new IllegalArgumentException("date not allowed to be null");
        if (pattern == null || "".equals(pattern.trim()))
            pattern = YYYY_MM_DD;
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 解析日期
     *
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date, String pattern) throws ParseException {
        if (date == null || "".equals(date.trim()))
            throw new IllegalArgumentException("date not allowed to be null or empty");
        if (pattern == null || "".equals(pattern.trim()))
            pattern = YYYY_MM_DD;
        return new SimpleDateFormat(pattern).parse(date);
    }

    /**
     * 以指定时间为基准，加减年
     *
     * @param date
     * @param addYears
     * @return
     */
    public static Date addYears(Date date, int addYears) {
        return add(date,addYears,0,0,0,0,0);
    }

    /**
     * 以指定时间为基准，加减月份
     *
     * @param date
     * @param addMonths
     * @return
     */
    public static Date addMonths(Date date, int addMonths) {
        return add(date,0,addMonths,0,0,0,0);
    }

    /**
     * 以指定时间为基准，加减天数
     *
     * @param date
     * @param addDays
     * @return
     */
    public static Date addDays(Date date, int addDays) {
        return add(date,0,0,addDays,0,0,0);
    }

    /**
     * 以指定时间为基准，加减小时
     *
     * @param date
     * @param addHours
     * @return
     */
    public static Date addHours(Date date, int addHours) {
        return add(date,0,0,0,addHours,0,0);
    }

    /**
     * 以指定时间为基准，加减分钟
     *
     * @param date
     * @param addMinutes
     * @return
     */
    public static Date addMinutes(Date date, int addMinutes) {
        return add(date,0,0,0,0,addMinutes,0);
    }

    /**
     * 以指定时间为基准，加减秒
     *
     * @param date
     * @param addSeconds
     * @return
     */
    public static Date addSeconds(Date date, int addSeconds) {
        return add(date,0,0,0,0,0,addSeconds);
    }

    public static Date add(Date date,int addYears,int addMonths,int addDays,int addHours,int addMinutes,int addSeconds) {
        if (null == date)
            throw new IllegalArgumentException("date not allowed to be null");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, addYears);
        c.add(Calendar.MONTH, addMonths);
        c.add(Calendar.DATE, addDays);
        c.add(Calendar.HOUR, addHours);
        c.add(Calendar.MINUTE, addMinutes);
        c.add(Calendar.SECOND, addSeconds);
        return c.getTime();
    }

    /**
     * 两个时间相差多少天
     *
     * @param minDate
     * @param maxDate
     * @return
     */
    public static int daysBetween(Date minDate, Date maxDate) {
        if (null == minDate)
            throw new IllegalArgumentException("date not allowed to be null");
        if (null == maxDate)
            throw new IllegalArgumentException("date not allowed to be null");
        Calendar cal = Calendar.getInstance();
        cal.setTime(minDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(maxDate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / 86400000L;
        return Math.abs(Integer.parseInt(String.valueOf(between_days)));
    }

    /**
     * 两个时间相差多少秒
     *
     * @param minDate
     * @param maxDate
     * @return
     */
    public static long secondsBetween(Date minDate, Date maxDate) {
        if (null == minDate)
            throw new IllegalArgumentException("date not allowed to be null");
        if (null == maxDate)
            throw new IllegalArgumentException("date not allowed to be null");
        Calendar cal = Calendar.getInstance();
        cal.setTime(minDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(maxDate);
        long time2 = cal.getTimeInMillis();
        long between_seconds = (time2 - time1) / 1000L;
        return between_seconds;
    }

    /**
     * 是否同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (null == date1)
            throw new IllegalArgumentException("date not allowed to be null");
        if (null == date2)
            throw new IllegalArgumentException("date not allowed to be null");
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);
        return c1.get(Calendar.ERA) == c2.get(Calendar.ERA)
                && c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取两个时间内的每一天的集合
     *
     * @param minDate
     * @param maxDate
     * @return
     */
    public static Date[] iteratorDate(Date minDate, Date maxDate) {
        if (null == minDate)
            throw new IllegalArgumentException("date not allowed to be null");
        if (null == maxDate)
            throw new IllegalArgumentException("date not allowed to be null");
        int daysBetween = daysBetween(minDate, maxDate);
        Date[] dates = new Date[daysBetween + 1];
        dates[0] = minDate;

        for (int i = 1; i <= daysBetween; ++i) {
            dates[i] = addDays(minDate, i);
        }
        return dates;
    }

    /**
     * 获取指定时间在月份中的第几天
     *
     * @param date
     * @return
     */
    public static int sumInMonth(Date date) {
        if (null == date)
            throw new IllegalArgumentException("date not allowed to be null");
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(date);
        return gcLast.get(Calendar.DATE);
    }

    /**
     * 以某个时间为准，获取前后某个月中的第几天
     *
     * @param date
     * @param addMonth
     * @param daySum
     * @return
     */
    public static Date getDateInMonth(Date date, int addMonth, int daySum) {
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(date);
        gcLast.add(Calendar.MONTH, addMonth);
        gcLast.set(Calendar.DATE, daySum);
        return gcLast.getTime();
    }

    /**
     * 是否为月份中的第一天
     *
     * @param date
     * @return
     */
    public static boolean isFirstInMonth(Date date) {
        if (null == date)
            throw new IllegalArgumentException("date not allowed to be null");
        int i = sumInMonth(date);
        return i == 1 ? true : false;
    }

    /**
     * 是否为月份中的最后一天
     *
     * @param date
     * @return
     */
    public static boolean isLastInMonth(Date date) {
        if (null == date)
            throw new IllegalArgumentException("date not allowed to be null");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH) == calendar
                .getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当天的开始时间，精确到毫秒
     *
     * @param date
     * @return
     */
    public static Date getFirstTime(Date date) {
        if (null == date)
            throw new IllegalArgumentException("date not allowed to be null");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当天的结束时间，精确到毫秒
     *
     * @param date
     * @return
     */
    public static Date getLastTime(Date date) {
        if (null == date)
            throw new IllegalArgumentException("date not allowed to be null");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 以指定时间为基准，获取前后某个月的开始时间，精确到毫秒
     *
     * @param addMonth
     * @return
     */
    public static Date getFirstTimeInMonth(Date date, int addMonth) {
        if (null == date)
            throw new IllegalArgumentException("date not allowed to be null");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, addMonth);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 以指定时间为基准，获取前后某个月的结束时间，精确到毫秒
     *
     * @param addMonth
     * @return
     */
    public static Date getLastTimeInMonth(Date date, int addMonth) {
        if (null == date)
            throw new IllegalArgumentException("date not allowed to be null");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, addMonth);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        System.out.println(formatDate(addDays(new Date(), 1), null));
    }
}
