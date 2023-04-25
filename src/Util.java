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

}
