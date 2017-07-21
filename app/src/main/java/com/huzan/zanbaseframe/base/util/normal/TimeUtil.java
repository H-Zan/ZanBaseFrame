package com.huzan.zanbaseframe.base.util.normal;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ZAN on 2017/7/19.
 *
 */

@SuppressLint("SimpleDateFormat")
public class TimeUtil {
    
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final java.lang.String MM_DD_HH_MM_SS = "MM-dd HH:mm:ss";
    private static final java.lang.String MM_DD = "MM-dd";
    private static final java.lang.String MM_SS = "mm:ss";
    private static final java.lang.String HH = "HH";
    
    /**
     * now
     * HH
     */
    public static String getHTime() {
        return getHTime(new Date());
    }
    
    /**
     * now
     * HH
     */
    public static String getHTime(Date date) {
        SimpleDateFormat mDateFormat = new SimpleDateFormat(HH);
        return mDateFormat.format(date);
    } 
    
    /**
     * now
     * yyyy-MM-dd HH:mm:ss
     */
    public static String getYMDHMS() {
        return getYMDHMS(new Date());
    }
    
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static String getYMDHMS(Date date) {
        SimpleDateFormat mDateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return mDateFormat.format(date);
    }

    /**
     * MM-dd HH:mm:ss
     */
    public static String getNowMDHMSTime() {
        SimpleDateFormat mDateFormat = new SimpleDateFormat(MM_DD_HH_MM_SS);
        return mDateFormat.format(new Date());
    }

    /**
     *  now 
     *  MM-dd
     */
    public static String getMD() {
        return getMD(new Date());
    }
    /**
     * MM-dd
     */
    public static String getMD(Date date) {
        
        SimpleDateFormat mDateFormat = new SimpleDateFormat(MM_DD);
        return mDateFormat.format(date);
    }
    
    /**
     * now 
     * yyyy-MM-dd
     */
    public static String getYMD() {
        return getYMD(new Date());
    }
    
    /**
     * yyyy-MM-dd
     */
    public static String getYMD(Date date) {

        SimpleDateFormat mDateFormat = new SimpleDateFormat(YYYY_MM_DD);
        return mDateFormat.format(date);
    }
    
    /**
     * mm:ss
     */
    public static String getMS(long time) {
        return getMS(new Date(time));
    }
    
    public static String getMS(Date date) {
        SimpleDateFormat mDateFormat = new SimpleDateFormat(MM_SS);
        return mDateFormat.format(date);
    }
    
    /**
     * 将时间转换为时间戳
     * @param data             待转换的日期
     * @param dataFormatPatten 待转换日期格式
     */
    public static long dateToTimeStamp(String data, String dataFormatPatten) {
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataFormatPatten, Locale.CHINA);
        Date date = null;
        try {
            date = simpleDateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert date != null;
        return date.getTime();
    }
    
    /**
     * 将时间戳转换为日期
     * @param time             待转换的时间戳
     * @param dataFormatPatten 转换出的日期格式
     */
    public static String timeStampToDate(long time, String dataFormatPatten) {
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataFormatPatten, Locale.CHINA);
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }
    
    /**
     * 日期转星期
     * @param dateString 日期
     * @return 周一 周二 周三 ...
     */
    public static String convertDataToWeek(String dateString) {
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD, Locale.CHINA);
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (isNow(date))
            return "今天";
        
        String[] weekDaysName = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }
    
    /**
     * 判断时间是不是今天
     * @return 是返回true，不是返回false
     */
    private static boolean isNow(Date date) {
        //当前时间
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD, Locale.CHINA);
        //获取今天的日期
        String nowDay = simpleDateFormat.format(now);
        //对比的时间
        String day = simpleDateFormat.format(date);
        return day.equals(nowDay);
    }
    
    /**
     * 是否在某个时间段内
     * @param date  要判断的时间
     * @param strDateBegin  开始时间
     * @param strDateEnd    结束时间
     * @return
     */
    public static boolean isInDate(Date date, String strDateBegin, String strDateEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(date);
        // 截取当前时间时分秒  
        int strDateH = Integer.parseInt(strDate.substring(11, 13));
        int strDateM = Integer.parseInt(strDate.substring(14, 16));
        int strDateS = Integer.parseInt(strDate.substring(17, 19));
        // 截取开始时间时分秒  
        int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
        int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
        int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
        // 截取结束时间时分秒  
        int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
        int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
        int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));
        if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {
            // 当前时间小时数在开始时间和结束时间小时数之间  
            if (strDateH > strDateBeginH && strDateH < strDateEndH) {
                return true;
                // 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间  
            } else if (strDateH == strDateBeginH && strDateM >= strDateBeginM
                       && strDateM <= strDateEndM) {
                return true;
                // 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间  
            } else if (strDateH == strDateBeginH && strDateM == strDateBeginM
                       && strDateS >= strDateBeginS && strDateS <= strDateEndS) {
                return true;
            }
            // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数  
            else if (strDateH >= strDateBeginH && strDateH == strDateEndH
                     && strDateM <= strDateEndM) {
                return true;
                // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数  
            } else if (strDateH >= strDateBeginH && strDateH == strDateEndH
                       && strDateM == strDateEndM && strDateS <= strDateEndS) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
