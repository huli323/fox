package foxutil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String FORMAT_1 = "yyyyMMddHHmmss";

    public static Date parseDate(String dateStr, String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatDate(Date date, String format){
        if(format == null || format.trim().length() == 0){
            return formatDate(date);
        }

        DateFormat dateFormat = new SimpleDateFormat(format);

        return dateFormat.format(date);
    }

    public static String formatDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat(FORMAT_1);

        return dateFormat.format(date);
    }


}
