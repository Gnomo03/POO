import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * This class represents a module that manages items, users, orders, and
 * carriers.
 */
public class Module {

    private HashMap<Integer, Item> soldItemsMap;
    private HashMap<Integer, Item> listedItemsMap;
    private TreeMap<Integer, User> userMap;
    private HashMap<Integer, Order> orderMap; // deve ter de passar a treemap por questões de eficiencia
    private TreeMap<String, Carrier> carrierMap;
    private LocalDate date;
    private double vintageProfit;

    private User currentUser;

    /**
     * Constructs a new Module object with empty maps and a current date.
     */
    Module() {

        this.soldItemsMap = new HashMap<Integer, Item>();
        this.listedItemsMap = new HashMap<Integer, Item>();
        this.userMap = new TreeMap<Integer, User>();
        this.orderMap = new HashMap<Integer, Order>();
        this.carrierMap = new TreeMap<String, Carrier>();
        this.date = LocalDate.now();
        this.vintageProfit = 0;
        User u = new User("admin", "admin", "admin", 0, "admin");
        this.addUser(u);

    }

    /**
     * Adds an user to the map.
     *
     * @param oneUser to the user map
     */
    public void addUser(User oneUser) {

        this.userMap.put(oneUser.getId(), oneUser.clone());

    }

    public void addNewItemToUsers(int id_user, int id_item) {

        this.userMap.get(id_user).addItem(this.listedItemsMap.get(id_item));

    }

    public void addNewEmittedOrderToUsers(int id_user, int id_order) {

        this.userMap.get(id_user).addEmittedOrder(this.orderMap.get(id_order));

    }

    public void addNewAquiredOrderToUsers(int id_user, int id_order) {

        this.userMap.get(id_user).addAcquireOrder(this.orderMap.get(id_order));

    }

    @Override
    public String toString() {
        return "Module{" +
                "soldItemsMap=" + soldItemsMap +
                ", listedItemsMap=" + listedItemsMap +
                ", userMap=" + userMap +
                ", orderMap=" + orderMap +
                ", carrierMap=" + carrierMap +
                ", date=" + date +
                ", vintageProfit=" + vintageProfit +
                '}';
    }

    // Item Manager Functions

    /**
     * Returns the map of sold items.
     * 
     * @return a HashMap where the keys are item IDs and the values are Item objects
     */
    public HashMap<Integer, Item> getSoldItemsMap() {
        return this.soldItemsMap;
    }

    /**
     * Returns the map of listed items.
     * 
     * @return a HashMap where the keys are item IDs and the values are Item objects
     */
    public HashMap<Integer, Item> getListedItemsMap() {
        return this.listedItemsMap;
    }

    /**
     * Returns Vintage's profit.
     * 
     * @return Vintage's profit
     */
    public double getVintageProfit() {
        return this.vintageProfit;
    }

    // User Manager Functions

    /**
     * This method returns the value of userMap
     * 
     * @return userMap
     */
    public TreeMap<Integer, User> getUserMap() {
        return this.userMap;
    }

    /**
     * This method returns the value of currentUser
     * 
     * @return currentUser
     */
    public User getCurrentUser() {
        return this.currentUser;
    }

    // Carrier Manager Functions

    /**
     * This method returns the value of carrierMap
     * 
     * @return carrierMap
     */
    public TreeMap<String, Carrier> getCarrierMap() {
        return this.carrierMap;
    }

    // Order Manager Functions

    /**
     * This method returns the value of orderMap
     * 
     * @return orderMap
     */
    public HashMap<Integer, Order> getOrderMap() {
        return this.orderMap;
    }
}
