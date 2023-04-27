import java.time.LocalDate;
import java.util.List;

public class Controller {

    private Model m;

    public Controller(Model m) {
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

    public boolean registItemBag(String description, String brand, double basePrice,
            String carrier, double conditionScore, double dimension,
            String material, LocalDate releaseDate) {

        return m.registBag(description, brand, basePrice,
                carrier, conditionScore, dimension, material, releaseDate,
                this.m.getCurrentUser().getId());
    }

    public boolean registItemTshirt(String description, String brand, double basePrice,
            String carrier, double conditionScore,
            Tshirt.TshirtSize size, Tshirt.TshirtPattern pattern) {
        return m.registTshirt(description, brand, basePrice, carrier,
                conditionScore, size, pattern,
                this.m.getCurrentUser().getId());
    }

    public boolean registItemSneaker(String description, String brand, double basePrice,
            String carrier, double conditionScore,
            double size, Sneaker.SneakerType type, String color, LocalDate releaseDate) {
        return m.registSneaker(description, brand, basePrice,
                carrier, conditionScore, size, type, color, releaseDate,
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

    @Override
    public String toString() {
        return m.toString();
    }

}