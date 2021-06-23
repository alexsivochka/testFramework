package at.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static String convertDateToFormat(String value, String patternOf, String patternTo) {
        String result = null;
        try {
            Locale locale = new Locale("uk", "UA");
            Date date = new SimpleDateFormat(patternOf, locale).parse(value);
            result = new SimpleDateFormat(patternTo, locale).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


}
