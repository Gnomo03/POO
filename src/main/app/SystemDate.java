package app;

import java.time.LocalDate;

public class SystemDate {
    public static LocalDate date;

    public static LocalDate getDate() {
        return date;
    }
    public static void setDate(LocalDate newDate) {
        date = newDate;
    }
    
}
