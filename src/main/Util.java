public class Util {

    public static Integer ToInteger(String Value) {
        Integer result = 0;
        try {
            result = Integer.parseInt(Value);
        } catch (Exception ex) {
            result = 0;
        }
        return result;
    }

    public static Double ToDouble(String Value) {
        Double result = 0.0;
        try {
            result = Double.parseDouble(Value);
        } catch (Exception ex) {
            result = 0.0;
        }
        return result;
    }
}