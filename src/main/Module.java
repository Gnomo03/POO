import java.time.LocalDate;
import java.util.List;

/**
 * This class represents a module that manages items, users, orders, and
 * carriers.
 */
public class Module {

    private ItemManager itemManager;
    private UserManager userManager;
    private OrderManager orderManager;
    private CarrierManager carrierManager;
    private LocalDate date;
    private double vintageProfit;
    private User currentUser;

    /**
     * Constructs a new Module object with Managers.
     */
    Module() {
        this.itemManager = new ItemManager();
        this.userManager = new UserManager();
        this.orderManager = new OrderManager();
        this.carrierManager = new CarrierManager();
        this.date = LocalDate.now();
        this.vintageProfit = 0;
    }

    public User getCurrentUser() {
        if (this.currentUser != null) {
            return this.currentUser.clone();
        } else {
            return null;
        }
    }

    public void setCurrentUser(int id) {
        this.currentUser = this.userManager.getUser(id);
    }

    public void addNewItemToUsers(int id_user, int id_item) {
        this.userManager.getUser(id_user).addItem(this.itemManager.getListedItems().get(id_item));
    }

    public void addNewEmittedOrderToUsers(int id_user, int id_order) {
        this.userManager.getUser(id_user).addEmittedOrder(this.orderManager.getOrder(id_order));
    }

    public void addNewAquiredOrderToUsers(int id_user, int id_order) {
        this.userManager.getUser(id_user).addAcquireOrder(this.orderManager.getOrder(id_order));
    }

    public Order makeOrder(int id_user, List<Integer> items_keys) {

        Order order = new Order();
        for (Integer current_key : items_keys) {
            Item i = this.itemManager.getListedItems().get(current_key);
            order.addItem(i);
            this.itemManager.updateItem(i.getID());
            User u = this.userManager.getUserMap().get(i.getUserId());
            u.itemUpdate(current_key);
        }
        this.orderManager.addOrder(order);
        return order.clone();
    }

    private boolean registsItem(Item item, int id_user) {
        User u = this.userManager.getUser(id_user);
        if (currentUser == null) {
            return false;
        }

        this.itemManager.addListedItem(item);
        Item i = this.itemManager.searchItem(item.getID());
        u.addItem(i);
        return true;

    }

    public void registsUser(User u) {

        this.userManager.addUser(u);

    }

    public boolean registBag(String description, String brand, String reference, double basePrice,
            double priceCorrection,
            String carrier, double conditionScore, int previousOwners, boolean premiumStat, double dimension,
            String material, int releaseDate, int userId) {

        Bag bag = new Bag(description, brand, reference, basePrice, priceCorrection,
                this.carrierManager.getCarrier(carrier),
                conditionScore, previousOwners, premiumStat, dimension, material, releaseDate, userId);
        registsItem(bag, userId);

        return registsItem(bag, userId);
    }

    public boolean registTshirt(String description, String brand, String reference, double basePrice,
            double priceCorrection,
            String carrier, double conditionScore, int previousOwners, boolean premiumStat, Tshirt.TshirtSize size,
            Tshirt.TshirtPattern pattern, int userId) {

        Tshirt tshirt = new Tshirt(description, brand, reference, basePrice, priceCorrection,
                this.carrierManager.getCarrier(carrier),
                conditionScore, previousOwners, premiumStat, size, pattern, userId);
        registsItem(tshirt, userId);

        return registsItem(tshirt, userId);
    }

    public boolean registSneaker(String description, String brand, String reference, double basePrice,
            double priceCorrection,
            String carrier, double conditionScore, int previousOwners, boolean premiumStat, double size,
            Sneaker.SneakerType type, String color, int releaseDate, int userId) {

        Sneaker sneaker = new Sneaker(description, brand, reference, basePrice, priceCorrection,
                this.carrierManager.getCarrier(carrier),
                conditionScore, previousOwners, premiumStat, size, type, color, releaseDate, userId);
        registsItem(sneaker, userId);

        return registsItem(sneaker, userId);
    }

    @Override
    public String toString() {
        return "Module{" +
                "soldItemsMap=" + this.itemManager.getSoldItems() +
                ", listedItemsMap=" + this.itemManager.getListedItems() +
                ", userMap=" + this.userManager.getUsers() +
                ", orderMap=" + this.orderManager.getOrders() +
                ", carrierMap=" + this.carrierManager.getCarriers() +
                ", date=" + date +
                ", vintageProfit=" + vintageProfit +
                '}';
    }

    public String SerializeUsers() {
        String result = "";
        for (User u : userManager.getUserMap().values()) {
            result += u.Serialize() + "\n";
        }
        return result;
    }

    public String DeSerializeUsers(List<String> Lines) {
        String result = "";

        this.userManager.getUserMap().clear();
        for (String line : Lines) {
            User u = new User();
            u.DeSerialize(line);
            this.userManager.addUser(u);
        }
        return result;
    }

    public User lookupUser(String email) {

        User u = userManager.findUserByEmail(email);
        if (u == null)
            return null;
        return u.clone();
    }

    public boolean reviewCredentials(String email) {
        User u = this.userManager.findUserByEmail(email);
        return u == null;
    }
}
