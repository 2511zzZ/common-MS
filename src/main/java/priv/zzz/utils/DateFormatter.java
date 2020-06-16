package priv.zzz.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateFormatter {

    final static String pattern = "YYYY/MM/dd HH:mm:ss";

    public static String format(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static String format(Calendar calendar){
        return format(calendar.getTime());
    }

    // 线程安全的实现
    public static String format(LocalDateTime now) {
        return now.format(DateTimeFormatter.ofPattern(pattern));
    }
}
