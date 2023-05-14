package app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

     /**
     * Writes the static variable
     *
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // default serialization
        out.writeObject(date); // serialize LocalDate
    }

    /**
     * Reads the static variable
     *
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // default deserialization
        date = (LocalDate) in.readObject(); // deserialize LocalDate
    }

}
