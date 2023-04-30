import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;

/**
 * This class represents a module that manages items, users, orders, and
 * carriers.
 */
public class Model {

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
    Model() {
        this.itemManager = new ItemManager();
        this.userManager = new UserManager();
        this.orderManager = new OrderManager();
        this.carrierManager = new CarrierManager();
        this.date = LocalDate.now();
        this.vintageProfit = 0;
    }

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

    public User getCurrentUser() {
        if (this.currentUser != null) {
            return this.currentUser.clone();
        } else {
            return null;
        }
    }
    public List<Order> checkThisUserOrders(int userId) {

        List<Order> pointers = this.orderManager.getThisUserOrders(userId);

        List <Order> orders = new LinkedList<Order>();

        for (Order order : pointers) {

            orders.add(order.clone());
        }
        return orders;
    }
    public void setCurrentUser(int id) {
        this.currentUser = this.userManager.getUser(id);
    }
    public void addNewItemToUsers(int id_user, int id_item) {
        this.userManager.getUser(id_user).addItem(this.itemManager.getItem(id_item));
    }

    public Order makeOrder(int id_user, List<Integer> items_keys) { // change

        Order order = new Order();
        
        for (Integer current_key : items_keys) {
            Item i = this.itemManager.getItem(current_key);
            User u = this.userManager.getUser(i.getUserId());
            order.addItem(i,u);
            this.itemManager.updateItem(i.getID());
        }
        User buyer = this.userManager.getUser(id_user);
        order.setBuyer(buyer);
        this.orderManager.addOrder(order);
        return order.clone();
    }

    private boolean registsItem(Item item,int id_user) {
        User u = this.userManager.getUser(id_user);
        if (currentUser == null) {return false;}
    
            this.itemManager.addListedItem(item); // clone no manager
            Item i = this.itemManager.searchItem(item.getID());
            u.addItem(i);
            i.setUserId(u.getId());
            return true;
        
    }
    public void registsUser(User u) {

        this.userManager.addUser(u);
        
    }

    public boolean registBag(String description, String brand, double basePrice,
            String carrier, double conditionScore, double dimension,
            String material, LocalDate releaseDate, int userId) {
                Stack<Integer> previousOwners = new Stack<Integer>();
        Bag bag = new Bag(description, brand, basePrice,
                this.carrierManager.getCarrier(carrier),
                conditionScore, previousOwners, dimension, material, releaseDate, userId);
       


        return registsItem(bag, userId);
    }

    public boolean registTshirt(String description, String brand, double basePrice,
            String carrier, double conditionScore, Tshirt.TshirtSize size,
            Tshirt.TshirtPattern pattern, int userId) {
                Stack<Integer> previousOwners = new Stack<Integer>();
        Tshirt tshirt = new Tshirt(description, brand, basePrice,
                this.carrierManager.getCarrier(carrier),
                conditionScore, previousOwners, size, pattern, userId);
        

        return registsItem(tshirt, userId);
    }

    public boolean registSneaker(String description, String brand, double basePrice,
            String carrier, double conditionScore, double size,
            Sneaker.SneakerType type, String color, LocalDate releaseDate, int userId) {
                Stack<Integer> previousOwners = new Stack<Integer>();
        Sneaker sneaker = new Sneaker(description, brand, basePrice,
                this.carrierManager.getCarrier(carrier),
                conditionScore, previousOwners, size, type, color, releaseDate, userId);
        

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

    public User lookupUser( String email){

        User u = userManager.findUserByEmail(email);
        if (u == null )return null;
        return u.clone();
    }
    public boolean reviewCredentials(String email) {
        User u = this.userManager.findUserByEmail(email);
        return u==null;
    }

 public void updateItemModel(int item_id) {

        Item i = this.itemManager.getItem(item_id);
        if (i != null) {

            this.vintageProfit += i.getPrice() * 0.112; // 0.122 Comissão por item da vintage
            
            this.itemManager.updateItem(item_id);
        }

    }
    public void alterItemState(int item_id, int user_id){  

        User u = this.userManager.getUser(user_id);
        u.listASystemItem(item_id);
        this.itemManager.soldToListed(item_id);
    }
    public void TimeSkip (LocalDate newDate){

            while (this.date.isBefore(newDate)){
                
               List <Order> orders = this.orderManager.getOrders();
                for ( Order o : orders){
                    if (o.isPending() && o.getDate().isBefore(newDate)){
                        List<Item> items = o.setFinished();
                        for (Item i : items){
                            
                            User u = this.userManager.getUser(i.getUserId());
                            u.removeItem(i); // o dinheiro só cai no utilizador apartir do momento que a encomenda é dispatched
                            User uBuy = o.getBuyer();
                            uBuy.addSystemItem(i);
                            updateItemModel(i.getID());
                            i.setUserId(uBuy.getId());

                        }
                        o.setFinished();
                    }
                    long daysBetween = ChronoUnit.DAYS.between(this.date, newDate);
                    if (o.isFinished() && daysBetween>=3){

                        HashMap<String, Integer> carrierHelper = o.getCarrierHelper();
                        for (String carrier_name : carrierHelper.keySet()){

                            Carrier c = this.carrierManager.getCarrier(carrier_name);
                            c.updateEarnings(carrierHelper.get(carrier_name),o.getItemPrice());

                        }
                        HashMap<String, Integer> carrierHashMap = o.getCarrierHelper();
       
                        for (User u : o.getSellers()){ 
                            List<Item> items = o.itemPerUser(u);
                            
                           
                                for(Item item : items){
                                    Bill bill = new Bill();
                                    bill.addItem(item,carrierHashMap.get(item.getCarrier().getName()));
                                    bill.setSold();
                                    bill.setOrder(o);
                                    u.addBills(bill.clone());
                                }
                            
                         }
        
                    Bill billBuyer = new Bill();
                    for (Item item : o.getCollection()){

                        billBuyer.addItem(item,carrierHashMap.get(item.getCarrier().getName()));

                     }
                    billBuyer.setBought();
                    billBuyer.setOrder(o);
                    o.getBuyer().addBills(billBuyer.clone());
                    o.setDispatched();

                    }

                }
              this.date = this.date.plusDays(1);
            }
            this.date = newDate;
    } 
    private void deleteBills(Order order) {

      TreeMap<Integer, User> it = this.userManager.getUserMap();

      for (int key : it.keySet()) {

        User u = it.get(key);
           for ( int keyBill: u.getBills().keySet()){

            Bill b = u.getBills().get(keyBill);
            if (b.getOrder().getID() == order.getID())
                u.getBills().remove(keyBill);

           }
      }

    }
    private void undoItem(Order o){

        List<Item> col = o.getCollection();

        for (Item item : col){
            User u = this.userManager.getUser(item.getUserId());
            u.removeSystemItem(item);
            
            item.returnOwnership();
            this.vintageProfit-=item.getPrice()*0.112;
            this.addNewItemToUsers(item.getUserId(),item.getID());
            this.itemManager.soldToListed(item.getID());

        }
    }
    public boolean deleteOrder(int orderId,int userId) { // Para uma order ser returend, nenhum dos items que foram comprados podem estar listados!
        
        Order o = this.orderManager.getOrder(orderId);
        long daysBetween = ChronoUnit.DAYS.between(o.getDate(), this.date);
        User u = o.getBuyer();
        for (Item i : o.getCollection()) {
            if (u.hasItem(i.getID()) == false) {
                return false;
            }

        }
        if (daysBetween >= 16 || !o.isDispatched())
            return false;

        this.orderManager.removeOrder(orderId);
        this.deleteBills(o);
        this.undoItem(o);
        return true;
    }
}
