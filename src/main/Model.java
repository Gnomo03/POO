import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * This class represents a module that manages items, users, orders, and
 * carriers.
 */
public class Model implements Serializable {

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

  
    public UserManager getUserManager() {
        return this.userManager;
    }
    public Map<Integer, User> getUserManagerCopy() {
        return this.userManager.getUserMapCopy();
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

    public User getCurrentUser() throws NullPointerException,UserIsAdminException {
       
            if (this.currentUser.getEmail().equals("admin")) {
                throw new UserIsAdminException(this.currentUser);
            }
            if (this.currentUser == null){
                throw new NullPointerException();
            }

        return this.currentUser;
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
    public void addNewItemToUsers(int id_user, int id_item) throws NullPointerException{

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

    private void registsItem(Item item,int id_user) throws NullPointerException {
        User u = this.userManager.getUser(id_user);
        
    
            this.itemManager.addListedItem(item); // clone no manager
            Item i = this.itemManager.searchItem(item.getID());
            u.addItem(i);
            i.setUserId(u.getId());
          
        
    }
    public void registsUser(User u) throws NullPointerException,UserAlreadyExistsException{
        if (reviewCredentials(u.getEmail()) != true) {
            throw new UserAlreadyExistsException();
        }
        this.userManager.addUser(u);
        
    }

    public void registBag(String description, String brand, double basePrice,
            String carrier, double conditionScore, double dimension,
            String material, LocalDate releaseDate, int userId) {
                Stack<Integer> previousOwners = new Stack<Integer>();
        Bag bag = new Bag(description, brand, basePrice,
                this.carrierManager.getCarrier(carrier),
                conditionScore, previousOwners, dimension, material, releaseDate, userId);
       


         registsItem(bag, userId);
    }

    public void registTshirt(String description, String brand, double basePrice,
            String carrier, double conditionScore, Tshirt.TshirtSize size,
            Tshirt.TshirtPattern pattern, int userId) {
                Stack<Integer> previousOwners = new Stack<Integer>();
        Tshirt tshirt = new Tshirt(description, brand, basePrice,
                this.carrierManager.getCarrier(carrier),
                conditionScore, previousOwners, size, pattern, userId);
        

       registsItem(tshirt, userId);
    }

    public void registSneaker(String description, String brand, double basePrice,
            String carrier, double conditionScore, double size,
            Sneaker.SneakerType type, String color, LocalDate releaseDate, int userId) {
                Stack<Integer> previousOwners = new Stack<Integer>();
        Sneaker sneaker = new Sneaker(description, brand, basePrice,
                this.carrierManager.getCarrier(carrier),
                conditionScore, previousOwners, size, type, color, releaseDate, userId);
        

        registsItem(sneaker, userId);
    }

    @Override
    public String toString() {
        return "Module{" +
                "Current User" + this.currentUser +
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
    private boolean reviewCredentials(String email) {
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
                            c.updateEarnings(carrierHelper.get(carrier_name),o.getItemPricePerCarrier(carrier_name));

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
    public void deleteOrder(int orderId,int userId) throws OrderNotReturnable { // Para uma order ser returend, nenhum dos items que foram comprados podem estar listados!
        
        Order o = this.orderManager.getOrder(orderId);
        long daysBetween = ChronoUnit.DAYS.between(o.getDate(), this.date);
        User u = o.getBuyer();
        for (Item i : o.getCollection()) {
            if (u.hasItem(i.getID()) == false) {
                throw new OrderNotReturnable();
            }

        }
        if (daysBetween <= 5 && daysBetween >= 16 || !o.isDispatched())
            throw new OrderNotReturnable();

        this.orderManager.removeOrder(orderId);
        this.userManager.deleteBills(o);
        this.undoItem(o);

    }
    public void save( String fileName) throws FileNotFoundException,IOException{

        
            FileOutputStream fs = new FileOutputStream(fileName);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(this);
            os.flush();
            os.close();
 
    }
    
    public static Model load( String fileName) throws FileNotFoundException,IOException,ClassNotFoundException{
        
        FileInputStream fs = new FileInputStream(fileName);
        ObjectInputStream os = new ObjectInputStream(fs);
        Model model = (Model) os.readObject();
        os.close();

        return model;
    }

    public void addCarrier(String name, double taxSmall, double taxMedium, double taxBig) throws CarrierAlreadyExistsException {
        
        this.carrierManager.addCarrier(new Carrier(name, taxSmall, taxMedium, taxBig,0));
    }

    public void changeCarrier(String name, double taxSmall, double taxMedium, double taxBig) throws NullPointerException {
        Carrier c = this.carrierManager.getCarrier(name);
        this.carrierManager.removeCarrier(name);
        c.setTaxSmall(taxSmall);
        c.setTaxMedium(taxMedium);
        c.setTaxBig(taxBig);
        try {
            this.carrierManager.addCarrier(c);
        }catch(CarrierAlreadyExistsException e) {}
        

    }


    public Map<String, Carrier> getCarrierManagerCopy() {
        return this.carrierManager.mapCopy();
    }


    public Double getVintageProfit() {
        return this.vintageProfit;
    }
}
