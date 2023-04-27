
//import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.io.File;
//import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class Controller {

    private static String USER_DATA_FILE = "user.data";
    private static String ITEM_DATA_FILE = "item.data";

    private Model m;

    public Controller(Model m) {
        this.m = m;
    }

    public User getCurrentUser() {
        return this.m.getCurrentUser();
    }

    public Carrier getCarrier(String Name) {
        return this.m.getCarrierManager().getCarrier(Name);
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

    public boolean registItemBag(String description, String brand, double basePrice,
            String carrier, double conditionScore, int previousOwners, double dimension,
            String material, LocalDate releaseDate) {

        return m.registBag(description, brand, basePrice,
                carrier, conditionScore, previousOwners, dimension, material, releaseDate,
                this.m.getCurrentUser().getId());
    }

    public boolean registItemTshirt(String description, String brand, double basePrice,
            String carrier, double conditionScore, int previousOwners,
            Tshirt.TshirtSize size, Tshirt.TshirtPattern pattern) {
        return m.registTshirt(description, brand, basePrice, carrier,
                conditionScore, previousOwners, size, pattern,
                this.m.getCurrentUser().getId());
    }

    public boolean registItemSneaker(String description, String brand, double basePrice,
            String carrier, double conditionScore, int previousOwners,
            double size, Sneaker.SneakerType type, String color, LocalDate releaseDate) {
        return m.registSneaker(description, brand, basePrice,
                carrier, conditionScore, previousOwners, size, type, color, releaseDate,
                this.m.getCurrentUser().getId());
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

    public void placeOrder(List<Integer> order) {

        m.makeOrder(m.getCurrentUser().getId(), order);

    }

    public String displayCarriers() {

        return m.getCarrierManagerList().toString();
    }

    public String displayListedItems() {

        return m.getListedItemsManagerList().toString();
    }

    public boolean saveData() {
        boolean result = false;
        try {
            // Users
            FileWriter fw = new FileWriter(USER_DATA_FILE);
            fw.write(this.m.getUserManager().serialize());
            // fw.write(m.toJson());
            fw.close();

            // Items
            fw = new FileWriter(ITEM_DATA_FILE);
            fw.write(this.m.getItemManager().serialize());
            fw.close();

            result = true;
        } catch (Exception ex) {
            // Avisar o utilizador

        }

        return result;
    }

    @Override
    public String toString() {
        return m.toString();
    }

    public boolean loadData() {
        boolean result = false;
        try {
            // Users
            File f = new File(USER_DATA_FILE);
            if (f.exists()) {
                List<String> lines = Files.readAllLines(Path.of(USER_DATA_FILE));
                m.getUserManager().deserialize(lines);
            }
            //
            f = new File(ITEM_DATA_FILE);
            if (f.exists()) {
                List<String> lines = Files.readAllLines(Path.of(ITEM_DATA_FILE));
                m.getItemManager().deserialize(lines);
            }

            // Others...
            result = true;
        } catch (Exception ex) {
        }
        return result;
    }
}