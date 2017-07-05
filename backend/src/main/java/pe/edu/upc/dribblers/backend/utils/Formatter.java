package pe.edu.upc.dribblers.backend.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alumnos on 6/21/17.
 */

public class Formatter {
    public static Date parseDate(String date){
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss");
        Date mDate = new Date();
        try {
            mDate = mFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mDate;
    }
    public static String parseDate(Date date){
        SimpleDateFormat format = new
                SimpleDateFormat("MMMM dd yyyy hh:mm aa", java.util.Locale.forLanguageTag("EN"));
        return format.format(date);
    }
}
