import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Controller {

    private static String USER_DATA_FILE = "user.data";
    private static String ITEM_DATA_FILE = "item.data";

    private Module m;

    public Controller(Module m) {
        this.m = m;
    }

    public User getCurrentUser() {
        return this.m.getCurrentUser();
    }

    public boolean login(String email, String password) {

        User u = m.lookupUser(email);
        if (u == null) {
            return false;
        }

        if (u.getPassword().equals(password)) {
            m.setCurrentUser(u.getId());
            return true;
        }

        return false;
    }

    public void logout() {
        m.setCurrentUser(-1);
    }

    /*
     * public void userRegistsItems(Item oneItem) {
     * 
     * }
     */

    public boolean registItemBag(String description, String brand, String reference, double basePrice, double priceCorrection,
    String carrier, double conditionScore, int previousOwners, boolean premiumStat, double dimension,
    String material, int releaseDate){
        
        return m.registBag(description, brand, reference, basePrice, priceCorrection,
        carrier,conditionScore, previousOwners, premiumStat, dimension,material,releaseDate,this.m.getCurrentUser().getId());
    }

    public boolean registItemTshirt(String description, String brand, String reference, double basePrice,
            double priceCorrection,
            Carrier carrier, double conditionScore, int previousOwners, boolean premiumStat,
            Tshirt.TshirtSize size, Tshirt.TshirtPattern pattern) {
       return false; // to be defined
    }

    public boolean registItemSneaker(String description, String brand, String reference, double basePrice,
            double priceCorrection,
            Carrier carrier, double conditionScore, int previousOwners, boolean premiumStat, int userId,
            double size, Sneaker.SneakerType type,
            String color, int date) {
            return false; // to be defined
    }

    public boolean registerUser(String email, String name, String address, int nif, String password) {

        if (this.m.reviewCredentials(email)) {
            User u = new User(email, name, address, nif, password);
            this.m.registsUser(u);
            return true;
        } else {
            return false;
        }
    }

    public boolean saveData() {
        boolean result = false;
        try {
            FileWriter fw = new FileWriter(USER_DATA_FILE);
            fw.write(this.m.SerializeUsers());
            // fw.write(m.toJson());
            fw.close();
            result = true;
        } catch (Exception ex) {
            // Avisar o utilizador

        }

        return result;
    }

    public boolean loadData() {
        boolean result = false;
        try {
            // Users
            List<String> lines = Files.readAllLines(Path.of(USER_DATA_FILE));
            m.DeSerializeUsers(lines);
            //
            // Others...
            result = true;
        } catch (Exception ex) {
        }
        return result;
    }
}