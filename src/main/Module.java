import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

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
    private  User currentUser;

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
<<<<<<< Updated upstream:src/main/Module.java
=======

    /// Managers -- Should be on Controller
    public UserManager getUserManager() {
        return this.userManager;
    }
    public LocalDate getDate() {
        return this.date;
    }


    public ItemManager getItemManager() {
        return itemManager;
    }

    public CarrierManager getCarrierManager() {
        return carrierManager;
    }

    public List<Carrier> getCarrierManagerList() {
        return carrierManager.getCarriers();
    }

    public List<Item> getListedItemsManagerList() {

        return itemManager.getListedItems();

    }
    /// -------------------

>>>>>>> Stashed changes:src/main/Model.java
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

    public void addNewAquiredOrderToUsers(int id_user, int id_order) {
        this.userManager.getUser(id_user).addAcquireOrder(this.orderManager.getOrder(id_order));
    }

    public Order makeOrder(int id_user, List<Integer> items_keys) { // change

        Order order = new Order();
        for (Integer current_key : items_keys) {
            Item i = this.itemManager.getListedItems().get(current_key);
            order.addItem(i);
            this.itemManager.updateItem(i.getID());
        }
        this.orderManager.addOrder(order);
        return order.clone();
    }

    private boolean registsItem(Item item,int id_user) {
        User u = this.userManager.getUser(id_user);
        if (currentUser == null) {return false;}
    
            this.itemManager.addListedItem(item);
            Item i = this.itemManager.searchItem(item.getID());
            u.addItem(i);
            return true;
        
    }
    public void registsUser(User u) {

        this.userManager.addUser(u);
        
    }

<<<<<<< Updated upstream:src/main/Module.java
    public boolean registBag(String description, String brand, String reference, double basePrice, double priceCorrection,
    String carrier, double conditionScore, int previousOwners, boolean premiumStat, double dimension,
    String material, int releaseDate,int userId){


        Bag bag = new Bag(description, brand, reference, basePrice, priceCorrection,this.carrierManager.getCarrier(carrier),
        conditionScore,previousOwners,premiumStat,dimension,material,releaseDate,userId);
=======
    public boolean registBag(String description, String brand, double basePrice,
            String carrier, double conditionScore, double dimension,
            String material, LocalDate releaseDate, int userId) {
                Stack<Integer> previousOwners = new Stack<Integer>();
        Bag bag = new Bag(description, brand, basePrice,
                this.carrierManager.getCarrier(carrier),
                conditionScore, previousOwners, dimension, material, releaseDate, userId);
>>>>>>> Stashed changes:src/main/Model.java
        registsItem(bag, userId);


        return registsItem(bag, userId);
<<<<<<< Updated upstream:src/main/Module.java
    }   
=======
    }

    public boolean registTshirt(String description, String brand, double basePrice,
            String carrier, double conditionScore, Tshirt.TshirtSize size,
            Tshirt.TshirtPattern pattern, int userId) {
                Stack<Integer> previousOwners = new Stack<Integer>();
        Tshirt tshirt = new Tshirt(description, brand, basePrice,
                this.carrierManager.getCarrier(carrier),
                conditionScore, previousOwners, size, pattern, userId);
        registsItem(tshirt, userId);

        return registsItem(tshirt, userId);
    }

    public boolean registSneaker(String description, String brand, double basePrice,
            String carrier, double conditionScore, double size,
            Sneaker.SneakerType type, String color, LocalDate releaseDate, int userId) {
                Stack<Integer> previousOwners = new Stack<Integer>();
        Sneaker sneaker = new Sneaker(description, brand, basePrice,
                this.carrierManager.getCarrier(carrier),
                conditionScore, previousOwners, size, type, color, releaseDate, userId);
        registsItem(sneaker, userId);

        return registsItem(sneaker, userId);
    }
>>>>>>> Stashed changes:src/main/Model.java

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
    public User lookupUser( String email){

        User u = userManager.findUserByEmail(email);
        if (u == null )return null;
        return u.clone();
    }
    public boolean reviewCredentials(String email) {
        User u = this.userManager.findUserByEmail(email);
        return u==null;
    }
<<<<<<< Updated upstream:src/main/Module.java
}
=======

 public void updateItemModel(int item_id) {

        Item i = this.itemManager.getItem(item_id);
        if (i != null) {

            this.vintageProfit += i.getPrice() * 0.112; // 0.122 Comissão por item da vintage
            i.addPreviousOwner(this.getId());
            removeItem(i);
        }

    }

    public void TimeSkip (LocalDate newDate){

            while (this.date.equals(newDate)){
                
               List <Order> orders = this.orderManager.getOrders();
                for ( Order o : orders){
                    if (o.isPending()){
                        List<Item> items = o.setFinished();
                        for (Item i : items){
                            
                            User u = this.userManager.getUserMap().get(i.getUserId());
                            u.itemUpdate(i.getID()); // o dinheiro só cai no utilizador apartir do momento que a encomenda é dispatched
                            updateItemModel(i.getID());

                        }
                        o.setFinished();
                    }
                    if (o.isFinished()){

                        HashMap<String, Integer> carrierHelper = o.getCarrierHelper();
                        for (String carrier_name : carrierHelper.keySet()){

                            Carrier c = this.carrierManager.getCarrier(carrier_name);
                            c.updateEarnings(carrierHelper.get(carrier_name),o.getItemPrice());

                        }
                        o.setDispatched();
                    }

                    
                    
                    // update vintage

                }
               
            }

    } 
}
>>>>>>> Stashed changes:src/main/Model.java
