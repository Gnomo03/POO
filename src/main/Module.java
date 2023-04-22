import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * This class represents a module that manages items, users, orders, and carriers.
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
    Module(){

        this.soldItemsMap = new HashMap<Integer, Item>();
        this.listedItemsMap = new HashMap<Integer, Item>();
        this.userMap = new TreeMap<Integer,User>();
        this.orderMap = new HashMap<Integer,Order>();
        this.carrierMap = new TreeMap<String, Carrier>();
        this.date = LocalDate.now();
        this.vintageProfit = 0;
        User u = new User("admin", "admin", "admin",0, "admin");
        this.addUser(u);

    }
    /**
     * Searches for an item with the specified ID.
     *
     */
    public User getCurrentUser(){

        return this.currentUser.clone();
    }
    public void setCurrentUser(int id){

        this.currentUser = this.userMap.get(id);

    }
    /**
     * Searches for an item with the specified ID.
     *
     * @param id the ID of the item to search for
     * @return the item with the specified ID, or null if it is not found
     */
    public Item searchItem(int id) {

        if (this.soldItemsMap.containsKey(id)) {
            
            Item item = this.soldItemsMap.get(id).clone();
                return item;

        }
        if (this.listedItemsMap.containsKey(id)) {

            Item item = this.listedItemsMap.get(id).clone();
                return item;

        }
        return null; 
    }
     /**
     * Adds an item to the sold items map.
     *
     * @param item the item to add to the sold items map
     */
    private void addSoldItem(Item item) {

        this.soldItemsMap.put(item.getID(), item);

    }
    /**
     * Adds an item to the listed items map.
     *
     * @param item the item to add to the listed items map
     */
    public void addListedItem(Item item) {

        this.listedItemsMap.put(item.getID(), item.clone());

    }
    /**
     * Removes an item from the listed items map.
     *
     * @param id the ID of the item to remove from the listed items map
     * @return the removed item
     */
    private Item removeListedItem(int id) {

        return this.listedItemsMap.remove(id);

    }
    /**
     * Removes an item from the sold items map.
     *
     * @param id the ID of the item to remove from the sold items map
     * @return the removed item
     */
    public Item removeSoldItem(int id) {
        return this.soldItemsMap.remove(id).clone();
    }
    /**
     * Updates the item with the specified ID from the listed items map to the sold items map,
     * and updates the vintage profit.
     *
     * @param id the ID of the item to update
     */
    public void updateItem(int id) {

        if (this.listedItemsMap.containsKey(id)) {
            
            Item item = this.removeListedItem(id);
            this.vintageProfit += item.getPrice()*0.08;
            this.addSoldItem(item);
            // updateCarrierProfit
        }

    }
     /**
     * Returns a list of all items in the sold items map.
     *
     * @return a list of all items in the sold items map
     */
    public List<Item> soldItemsList(){

        List<Item> items = new LinkedList<Item>();
        
        for (Integer key : this.soldItemsMap.keySet()) {
            Item value = this.soldItemsMap.get(key);
            items.add(value.clone());
        }

        return items;
    }
     /**
     * Returns a list of all items currently listed .
     *
     * @return a list of all items currently listed
     */
    public List<Item> listedItemsList(){

        List<Item> items = new LinkedList<Item>();
        
        for (Integer key : this.listedItemsMap.keySet()) {
            Item value = this.listedItemsMap.get(key);
            items.add(value.clone());
        }

        return items;
    }
    /**
     * Adds an user to the map.
     *
     * @param oneUser to the user map
     */
    public void addUser(User oneUser){

        this.userMap.put(oneUser.getId(), oneUser.clone());

    }
    /**
     * removes an user to the map.
     *
     * @param id of the user
     * @return the user that was removed
     */
    public User removeUser(int id){

        return this.userMap.remove(id).clone();

    }
    /**
     * Returns every sales of an user
     *
     * @param id of the user
     * @return the sales of an user in a List
     */
    public List<Order> getUserSales(int id){

        User u = this.userMap.get(id);
        List<Order> orders = new LinkedList<Order>();

        for(Order order :u.getEmittedOrder())
            orders.add(order.clone());

        return orders;
    }
    /**
     * Returns a specific order
     *
     * @param id of the order
     * @return copy of the order
     */
    public Order searchOrder(int id) {
        return this.orderMap.get(id).clone();
    }
    /**
     * Add´s a order to the order map
     *
     * @param order
     */
    public void addOrder(Order order) {
        this.orderMap.put(order.getID(),order.clone());
    }
    /**
     * Removes´s a order to the order map
     *
     * @param id of the order
     * @return the order;
     */
    public Order removeOrder(int id) {
        return this.orderMap.remove(id).clone();
    }
    /**
     * Returns a specific Carrier
     *
     * @param id of the carrier
     * @return copy of the carrier
     */
    public Carrier searchCarrier(String id) {

        return this.carrierMap.get(id).clone();

    }
    /**
     * Add´s a carrier to the carrier map
     *
     * @param carrier
     */
    public void addCarrier(Carrier carrier) {
        this.carrierMap.put(carrier.getName(),carrier.clone());
    }
    public void updateCarrierProfit(String name) {
        //to be defined
    }
    public void addNewItemToUsers(int id_user,int id_item) {

        this.userMap.get(id_user).addItem(this.listedItemsMap.get(id_item));

    }
    public void addNewEmittedOrderToUsers(int id_user,int id_order) {

        this.userMap.get(id_user).addEmittedOrder(this.orderMap.get(id_order));

    }
    public void addNewAquiredOrderToUsers(int id_user,int id_order) {

        this.userMap.get(id_user).addAcquireOrder(this.orderMap.get(id_order));

    }
    public void updateOrders(){

        //to be defined
        this.vintageProfit = 0;

    }
    public void makeOrder(int id_user,List<Integer> items_keys){

        Order order = new Order();
        for(Integer current_key : items_keys){
            Item i = this.listedItemsMap.get(current_key);
            order.addItem(i);
            this.updateItem(i.getID());
            User u = this.userMap.get(i.getUserId());
            u.itemUpdate(current_key);
        }
        this.addOrder(order);
    }
    public boolean reviewCredentials(String email){ 

        boolean ret = true;
        for (Integer user_id : this.userMap.keySet()){

            User u = this.userMap.get(user_id);
            if (u.getEmail().equals(email))
                ret = false;

        } 
        return ret;
    }
    
    public User findUserByEmail(String email){

        for (Integer user_id : this.userMap.keySet()){

            User temp = this.userMap.get(user_id);
            if (temp.getEmail().equals(email))
                return temp.clone();

        } 
        return null;
    }
    public boolean userRegistsItem(String email, Item item,String carrierName){

        User u = this.findUserByEmail(email);
        if (u == null)return false;
        else{
            item.setUserId(u.getId());
            item.setCarrier(this.carrierMap.get(carrierName));
            this.addListedItem(item);
            Item i = searchItem(item.getID());
            u.addItem(i);
            return true;
        }

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
}
    
