import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Util {
    
    public static LocalDate toDate(String Value) {
        // Define the expected format of the date string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {

            LocalDate date = LocalDate.parse(Value, formatter);
            return date;
        } catch (DateTimeParseException e) {

            throw new IllegalArgumentException("Invalid date format. Expected format: yyyy-MM-dd");
        }
    }


    public static Tshirt.TshirtSize toTshirtSize(String Value) throws IllegalArgumentException {
        Tshirt.TshirtSize result = null;
        try {
            result = Tshirt.TshirtSize.valueOf(Value);
        } catch (Exception ex) {
           
            throw new IllegalArgumentException("Tshirt Size must be one of the following: S,M,L,XL\n");
        }
        return result;
    }

    public static Tshirt.TshirtPattern toTshirtPattern(String Value) throws IllegalArgumentException {
        Tshirt.TshirtPattern result = null;
        try {
            result = Tshirt.TshirtPattern.valueOf(Value);
        } catch (Exception ex) {
            
            throw new IllegalArgumentException("Tshirt Size must be one of the following: Smooth, Stripes, PalmTrees\n");
        }
        return result;
    }

    public static Sneaker.SneakerType toSneakerType(String Value)throws IllegalArgumentException {
        Sneaker.SneakerType result = null;
        try {
            result = Sneaker.SneakerType.valueOf(Value);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Sneaker must be one of the following: LACES,NOLACES\n");
        }
        return result;
    }

    public static List<Integer> toLinkedList(String input) {
        String[] parts = input.split(",");
        List<Integer> list = new LinkedList<>();
        for (String part : parts) {
            Integer value = Integer.parseInt(part.trim());
            list.add(value);
        }
        return list;
    }


    
}