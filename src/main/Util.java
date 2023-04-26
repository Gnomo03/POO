import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Util {
    public static Integer ToInteger(String Value){
        Integer result = 0;
        try{
            result = Integer.parseInt(Value);
        }
        catch(Exception ex){
            result=0;
        }
        return result;
    }
    
    public static Double ToDouble(String Value){
        Double result = 0.0;
        try{
            result = Double.parseDouble(Value);
        }
        catch(Exception ex){
            result = 0.0;
        }
        return result;
    }
    public static LocalDate ToDate(String Value){
         // Define the expected format of the date string
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

         try {
             
             LocalDate date = LocalDate.parse(Value, formatter);
             return date;
         } catch (DateTimeParseException e) {
             
             throw new IllegalArgumentException("Invalid date format. Expected format: yyyy-MM-dd");
         }
     }
    

    public static boolean ToBoolean(String Value){
        boolean result = false;
        try{
            result = Boolean.parseBoolean(Value);
        }
        catch(Exception ex){
            result = false;
        }
        return result;
    }

    public static Tshirt.TshirtSize toTshirtSize(String Value){
        Tshirt.TshirtSize result = null;
        try{
            result = Tshirt.TshirtSize.valueOf(Value);
        }
        catch(Exception ex){
            result = null;
        }
        return result;        
    }


    public static Tshirt.TshirtPattern toTshirtPattern(String Value){
        Tshirt.TshirtPattern result = null;
        try{
            result = Tshirt.TshirtPattern.valueOf(Value);
        }
        catch(Exception ex){
            result = null;
        }
        return result;        
    } 

    public static Sneaker.SneakerType toSneakerType(String Value){
        Sneaker.SneakerType result = null;
        try{
            result = Sneaker.SneakerType.valueOf(Value);
        }
        catch(Exception ex){
            result = null;
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

    public static String[] Split(String delimiter, String Text){
        String safeDelim = Pattern.quote(delimiter);
        String [] result = Text.split(safeDelim);
        return result;
    }
}
