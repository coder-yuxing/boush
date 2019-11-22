package com.dabanjia.boush.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * 日期处理类
 *
 * @author GuangRen
 * @date 2019/11/19
 */
public class DateUtils {

    private DateUtils() {}
    /**
     * 默认日期格式: yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_PATTERN_0 = "yyyy-MM-dd HH:mm:ss:SSS";

    /**
     * 默认日期格式: yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_PATTERN_1 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式2: yyyy/MM/dd HH:mm:ss
     */
    public static final String DATE_PATTERN_2 = "yyyy/MM/dd HH:mm:ss";

    /**
     * 日期格式3: yyyyMMddHHmmssSSS
     */
    public static final String DATE_PATTERN_3 = "yyyyMMddHHmmssSSS";

    /**
     * 日期格式4: yyyyMMddHHmmss
     */
    public static final String DATE_PATTERN_4 = "yyyyMMddHHmmss";

    /**
     * 日期格式5: yyyyMMdd
     */
    public static final String DATE_PATTERN_5 = "yyyyMMdd";

    /**
     * 获取当前日期并格式化
     * <p>
     *     格式：yyyy-MM-dd HH:mm:ss
     *     实例：2019-07-01 15:36:41
     * <p/>
     *
     * @return 当前日期
     */
    public static String formatDateTime() {
        return formatDateTime(DATE_PATTERN_1);
    }

    /**
     * 获取当前日期并按指定格式进行格式化
     *
     * @return 当前日期
     */
    public static String formatDateTime(String format) {
        Objects.requireNonNull(format);

        return formatDateTime(LocalDateTime.now(), format);
    }

    /**
     * 获取格式化后的指定日期字符串
     * <p>
     *     格式：yyyy-MM-dd HH:mm:ss
     *     实例：2019-07-01 15:36:41
     * <p/>
     *
     * @param millis 日期
     * @return 格式化后的指定日期字符串
     */
    public static String formatDateTime(Long millis) {
        return formatDateTime(millis, DATE_PATTERN_1);
    }

    /**
     * 将指定日期转换为指定格式
     *
     * @param millis  日期
     * @param pattern 日期格式
     * @return 格式化后的指定日期字符串
     */
    public static String formatDateTime(Long millis, String pattern) {
        Objects.requireNonNull(millis);
        Objects.requireNonNull(pattern);

        LocalDateTime localDateTime = parseMillis2LocalDateTime(millis);
        return formatDateTime(localDateTime, pattern);
    }

    /**
     * 将指定日期转换为指定格式
     *
     * @param date   日期
     * @param format 日期格式
     * @return 格式化后的指定日期字符串
     */
    public static String formatDateTime(Date date, String format) {
        Objects.requireNonNull(date);

        return formatDateTime(date.getTime(), format);
    }

    /**
     * 将指定日期转换为指定格式
     *
     * @param localDateTime 日期
     * @param format        日期格式
     * @return 格式化后的指定日期字符串
     */
    public static String formatDateTime(LocalDateTime localDateTime, String format) {
        Objects.requireNonNull(localDateTime);
        Objects.requireNonNull(format);

        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 将某时间字符串转为自定义时间格式的LocalDateTime
     *
     * @param date   日期
     * @param format 日期格式
     * @return LocalDateTime 类型日期
     */
    public static LocalDateTime parseString2LocalDateTime(String date, String format) {
        Objects.requireNonNull(date);
        Objects.requireNonNull(format);

        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 将某时间字符串转为自定义时间格式的Date
     *
     * @param date   日期
     * @param format 日期格式
     * @return Date 类型日期
     */
    public static Date parseString2Date(String date, String format) {
        LocalDateTime localDateTime = parseString2LocalDateTime(date, format);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }


    /**
     * 字符串类型日期转为时间戳
     *
     * @param date   日期
     * @param format 日期格式
     * @return 时间戳
     */
    public static long parseString2Millis(String date, String format) {
        LocalDateTime localDateTime = parseString2LocalDateTime(date, format);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return zonedDateTime.toInstant().toEpochMilli();
    }

    /**
     * 毫秒级时间戳转换为 LocalDateTime类型日期
     *
     * @param millis 毫秒级时间戳
     * @return LocalDateTime类型日期
     */
    public static LocalDateTime parseMillis2LocalDateTime(long millis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
    }

    /**
     * 获取指定日期的年份
     *
     * @param millis 指定日期
     * @return 指定时间的年份
     */
    public static int getYear(long millis) {
        return parseMillis2LocalDateTime(millis).getYear();
    }

    /**
     * 获取指定日期的年份
     *
     * @param date   指定日期
     * @param format 指定日期的格式
     * @return 指定时间的年份
     */
    public static int getYear(String date, String format) {
        LocalDateTime localDateTime = parseString2LocalDateTime(date, format);
        return localDateTime.getYear();
    }

    /**
     * 获取指定日期的月份
     *
     * @param millis 指定日期
     * @return 指定时间的月份
     */
    public static int getMonth(long millis) {
        return parseMillis2LocalDateTime(millis).getMonth().getValue();
    }

    /**
     * 获取指定日期的月份
     *
     * @param date   指定日期
     * @param format 指定日期的格式
     * @return 指定时间的月份
     */
    public static int getMonth(String date, String format) {
        LocalDateTime localDateTime = parseString2LocalDateTime(date, format);
        return localDateTime.getMonth().getValue();
    }

    /**
     * 获取指定日期当月的日
     *
     * @param millis 指定日期
     * @return 指定时间的日
     */
    public static int getDayOfMonth(long millis) {
        return parseMillis2LocalDateTime(millis).getDayOfMonth();
    }

    /**
     * 获取指定日期当月的日
     *
     * @param date   指定日期
     * @param format 指定日期的格式
     * @return 指定时间的日
     */
    public static int getDayOfMonth(String date, String format) {
        LocalDateTime localDateTime = parseString2LocalDateTime(date, format);
        return localDateTime.getDayOfMonth();
    }

    /**
     * 是否是今天
     *
     * @param millis 毫秒数
     * @return true 是 || false 否
     */
    public static boolean isToday(final long millis) {
        return getDayOfMonth(millis) == getDayOfMonth(System.currentTimeMillis());
    }

    /**
     * 是否是指定日期
     *
     * @param millis 待判断时间戳
     * @param appointedMillis 指定时间毫秒
     * @return
     */
    public static boolean isTheDay(final long millis, final long appointedMillis) {
        return millis >= DateUtils.dayBegin(appointedMillis)
                && millis <= DateUtils.dayEnd(appointedMillis);
    }

    /**
     * 获取指定时间的那天 00:00:00.000 的时间
     *
     * @param millis 毫秒数
     * @return
     */
    public static long dayBegin(final long millis) {

        Calendar c = Calendar.getInstance();
        c.setTime(new Date(millis));
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime().getTime();
    }

    /**
     * 获取指定时间的那天 23:59:59.999 的时间
     *
     * @param millis 毫秒数
     * @return
     */
    public static long dayEnd(final long millis) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(millis));
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime().getTime();
    }

    /**
     * 获取明天的0的时间对象
     *
     * @return
     */
    public static Date getNextDayBeginTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 获取今天00:00:00.000的时间戳
     *
     * @return 时间戳
     */
    public static long getTodayStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime().getTime();
    }

    /**
     * 获取今天23:59:59.999时间戳
     *
     * @return 时间戳
     */
    public static long getTodayEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime().getTime();
    }

    /**
     * 获取某年某月的第一天毫秒数
     *
     * @param year
     * @param month
     * @return
     */
    public static long getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return cal.getTime().getTime();
    }

    /**
     * 获取某年某月的最后一天毫秒数
     *
     * @param year
     * @param month
     * @return
     */
    public static long getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime().getTime();
    }

    /**
     * 获取某年第一天毫秒数
     *
     * @param year 年份
     * @return
     */
    public static long getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime().getTime();
    }

    /**
     * 获取某年最后一天毫秒数
     *
     * @param year 年份
     * @return Date
     */
    public static long getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        return calendar.getTime().getTime();
    }

    /**
     * 获取前一年当天毫秒数
     *
     * @param millis 毫秒数
     * @return
     */
    public static long getYearBefore(long millis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        cal.add(Calendar.YEAR, -1);
        return cal.getTime().getTime();
    }

}
