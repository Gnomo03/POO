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

    public void addNewItemToUsers(int id_user, int id_item) {
        this.userManager.getUser(id_user).addItem(this.itemManager.getListedItems().get(id_item));
    }

    public void addNewEmittedOrderToUsers(int id_user, int id_order) {
        this.userManager.getUser(id_user).addEmittedOrder(this.orderManager.getOrder(id_order));
    }

    public void addNewAquiredOrderToUsers(int id_user, int id_order) {
        this.userManager.getUser(id_user).addAcquireOrder(this.orderManager.getOrder(id_order));
    }

    public void makeOrder(int id_user, List<Integer> items_keys) {

        Order order = new Order();
        for (Integer current_key : items_keys) {
            Item i = this.itemManager.getListedItems().get(current_key);
            order.addItem(i);
            this.itemManager.updateItem(i.getID());
            User u = this.userManager.getUserMap().get(i.getUserId());
            u.itemUpdate(current_key);
        }
        this.orderManager.addOrder(order);
    }

    public boolean userRegistsItem(String email, Item item, String carrierName) {

        User u = this.userManager.findUserByEmail(email);
        if (u == null)
            return false;
        else {
            item.setUserId(u.getId());
            item.setCarrier(this.carrierManager.getCarrier(carrierName));
            this.itemManager.addListedItem(item);
            Item i = this.itemManager.searchItem(item.getID());
            u.addItem(i);
            return true;
        }
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
}
