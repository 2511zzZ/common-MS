package priv.zzz.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatter {

    public static String format(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static String format(Calendar calendar){
        return format(calendar.getTime());
    }
}
