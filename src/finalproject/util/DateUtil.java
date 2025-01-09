package finalproject.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public static String getCurrentDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return dateFormatter.format(getNow());
    }

    private static LocalDateTime getNow(){
        return  LocalDateTime.now();
    }

}
