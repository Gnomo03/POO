package app;

import java.io.Serializable;
import java.time.LocalDate;

public class SystemDate implements Serializable  {
    private static LocalDate date;

    public static LocalDate getDate() {
        return date;
    }

    public static void setDate(LocalDate newDate) {
        date = newDate;
    }

}
