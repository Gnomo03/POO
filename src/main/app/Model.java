package app;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;


import java.io.*;

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
    
    public String getDate() {
        return this.date.toString();
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

    private User getCurrentUser() throws NullPointerException,UserIsAdminException {
       
            if (this.currentUser.getEmail().equals("admin")) {
                throw new UserIsAdminException(this.currentUser);
            }
            if (this.currentUser == null){
                throw new NullPointerException();
            }

        return this.currentUser;
    }
    public User CurrentUser() throws NullPointerException,UserIsAdminException {
       
        if (this.currentUser.getEmail().equals("admin")) {
            throw new UserIsAdminException(this.currentUser);
        }
        if (this.currentUser == null){
            throw new NullPointerException();
        }

    return this.currentUser.clone();
}
    public String currentUserSystemItems() throws UserIsAdminException {

        return getCurrentUser().getSystemItems().toString();
    }
    public String currentUserListedItems()throws UserIsAdminException {

        return getCurrentUser().getSellingItems().toString();
    }
    public String checkThisUserOrders() {

        int userId = this.currentUser.getId();
        List<Order> pointers = this.orderManager.getThisUserOrders(userId);

        List <Order> orders = new LinkedList<Order>();

        for (Order order : pointers) {

            orders.add(order.clone());
        }
        return orders.toString();
    }
    private void setCurrentUser(int id) {
        this.currentUser = this.userManager.getUser(id);
    }
    private void addNewItemToUsers(int id_user, int id_item) throws NullPointerException{

        this.userManager.getUser(id_user).addItem(this.itemManager.getItem(id_item));
    }
    public void loginModel(String email, String password) throws NullPointerException,MissedIdException{

        User u = lookupUser(email);
        

        if (u.getPassword().equals(password)) {
            setCurrentUser(u.getId());
        }else{
            throw new MissedIdException();
        }

    }
    private String displayNormalCarriers(){ 
        String result = "\n";
        for (Carrier c : getCarrierManagerList()) {
            if (c instanceof Premium){
                
            }else{
                result += c.toString() + "\n";
            }

            
        }
        return result;

    }
    private String displayPremiumCarriers(){ 
        String result = "\n"; 
        for (Carrier c : getCarrierManagerList()) {
            if (c instanceof Premium){
                PremiumCarrier c1 = (PremiumCarrier) c;
                result += c1.toString() + "\n";
            }
                
        }
        return result;

    }
    public String showCarriers(String premium) {
        if (premium.equals("y"))
        return displayPremiumCarriers();
        else
        return displayNormalCarriers();
    }
    public String displayListedItems() throws UserIsAdminException  { 
        List <Item> items = getListedItemsManagerList();
        List <Item> ret = new LinkedList<Item>();
        for (Item item : items) {

            if (item.getUserId() != getCurrentUser().getId())
                    ret.add(item);                
        }
        return ret.toString();
    }
    public String displayAllCarriers() { 
        String result = "\n";
        for (Carrier c : getCarrierManagerList()) {
            System.out.println(c instanceof Premium);
            if (c instanceof Premium){
                PremiumCarrier c1 = (PremiumCarrier) c;
                result += c1.toString() + "\n";
            }else{
                result += c.toString() + "\n";
            }
        }
        return result;
    }
    private void setCurrentDate(LocalDate dateNew){
        this.date = dateNew;
    }
    public Order makeOrder(List<Integer> items_keys)throws InvalidId { 


        if (this.currentUser.oneOfHis(items_keys) || !this.itemManager.areAllThisForSale(items_keys) || items_keys.isEmpty())
            throw new InvalidId();

        Order order = new Order();        
        for (Integer current_key : items_keys) {
            Item i = this.itemManager.getItem(current_key);
            User u = this.userManager.getUser(i.getUserId());
            order.addItem(i,u);
            this.itemManager.updateItem(i.getID());
        }
        User buyer = this.userManager.getUser(this.currentUser.getId());
        order.setBuyer(buyer);
        order.setDate(date);
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
    public void registsUser(String email, String name, String address, int nif, String password)throws NullPointerException,UserAlreadyExistsException{

        if (reviewCredentials(email) != true) {
            throw new UserAlreadyExistsException();
        }
        User u = new User(email, name, address, nif, password);
        this.userManager.addUser(u);
        
    }

    public void registBag(String description, String brand, double basePrice,
            String carrier, double conditionScore, double dimension,
            String material, LocalDate releaseDate,String premium) throws NullPointerException,IllegalArgumentException {
                Stack<Integer> previousOwners = new Stack<Integer>();
        
                if (currentUser == null)
                    throw new NullPointerException();


                    if (conditionScore > 5)
                    throw new IllegalArgumentException();
        

        if (premium.equals("y")){
            PremiumBag bag = new PremiumBag(description, brand, basePrice,
                this.carrierManager.getCarrier(carrier),
                conditionScore/5, previousOwners, dimension, material, releaseDate, this.currentUser.getId());
            
                registsItem(bag, this.currentUser.getId());
        }else{
            Bag bag = new Bag(description, brand, basePrice,
                this.carrierManager.getCarrier(carrier),
                conditionScore/5, previousOwners, dimension, material, releaseDate, this.currentUser.getId());
                registsItem(bag, this.currentUser.getId());
        }
        
       
    }

    public void registTshirt(String description, String brand, double basePrice,
            String carrier, double conditionScore, Tshirt.TshirtSize size,
            Tshirt.TshirtPattern pattern) throws NullPointerException,IllegalArgumentException{

                if (currentUser == null)
                    throw new NullPointerException();

                if (conditionScore > 5)
                    throw new IllegalArgumentException();
        

                Stack<Integer> previousOwners = new Stack<Integer>();
        Tshirt tshirt = new Tshirt(description, brand, basePrice,
                this.carrierManager.getCarrier(carrier),
                conditionScore/5, previousOwners, size, pattern, this.currentUser.getId());
                
                

       registsItem(tshirt, this.currentUser.getId());
    }

    public void registSneaker(String description, String brand, double basePrice,
            String carrier, double conditionScore, double size,
            Sneaker.SneakerType type, String color, LocalDate releaseDate, String premium) throws NullPointerException,IllegalArgumentException {

                if (currentUser == null)
                throw new NullPointerException();

                Stack<Integer> previousOwners = new Stack<Integer>();

            if (conditionScore > 5)
            throw new IllegalArgumentException();


        if(premium.equals("y")){
            PremiumSneaker sneaker = new PremiumSneaker(description, brand, basePrice,
                this.carrierManager.getCarrier(carrier),
                conditionScore/5, previousOwners, size, type, color, releaseDate, this.currentUser.getId());
        

        registsItem(sneaker, this.currentUser.getId());

        }else{
            Sneaker sneaker = new Sneaker(description, brand, basePrice,
                this.carrierManager.getCarrier(carrier),
                conditionScore/5, previousOwners, size, type, color, releaseDate, this.currentUser.getId());
        

        registsItem(sneaker,this.currentUser.getId());
        }
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
    public void alterItemState(int item_id) throws NullPointerException{  
        int user_id = currentUser.getId();
        User u = this.userManager.getUser(user_id);
        u.listASystemItem(item_id);
        this.itemManager.soldToListed(item_id);
    }
    public void TimeSkip (LocalDate newDate)throws IllegalArgumentException{ 
        
        if ( newDate.isBefore(this.date)){
            throw new IllegalArgumentException();
        }

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
            Carrier c = item.getCarrier();
            c.revertProfit(item.getPrice(),o.getCarrierHelper().get(c.getName()));

        }
    }
    public void deleteOrder(int orderId) throws OrderNotReturnable { // Para uma order ser returend, nenhum dos items que foram comprados podem estar listados!
        
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

    public void addCarrier(String name, double taxSmall, double taxMedium, double taxBig,String premium) throws CarrierAlreadyExistsException {

        if (premium.equals("y")){

            this.carrierManager.addCarrier(new PremiumCarrier(name, taxSmall, taxMedium, taxBig,0));
        }
          else {
            this.carrierManager.addCarrier(new Carrier(name, taxSmall, taxMedium, taxBig,0));
          }

    
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


    public void nullCurrentUser() {
        this.currentUser = null;
    }


    private void parserExecuter(String buffer,int line) throws InvalidCommand,IllegalArgumentException{
        try {
            String[] substrings = buffer.split(",");
        if (substrings[0].equals("SetupDate")){
           setCurrentDate(Util.toDate(substrings[1]));
            return;
        }
       
        
        LocalDate dateParse = Util.toDate(substrings[0]);
        if (dateParse.isBefore(this.date)){
            TimeSkip(dateParse);
        }
    
        switch (substrings[1]) {

            case "RegistarUtilizador":
            String[] arguments = substrings[2].split(";");
            try {
            registsUser(arguments[0],arguments[1], arguments[2],Integer.parseInt(arguments[3]), arguments[4]);
            }catch(UserAlreadyExistsException e){
                throw new InvalidCommand(substrings[1],line);
            }
            break;


            case "Login":
            String[] arguments1 = substrings[2].split(";");
            try {
                loginModel(arguments1[0], arguments1[1]); 
            }catch(MissedIdException e){
                throw new InvalidCommand(substrings[1],line);
            }
            catch(NullPointerException e){
                throw new InvalidCommand(substrings[1],line);
            }
            break;

            case "RegistarItem":
            String[] arguments2 = substrings[2].split(";");
            try {
                switch (arguments2[0]) {
                    case "Bag":
                        
                        if (arguments2[10].equals("No")){
                            double dimension = Double.parseDouble(arguments2[5]) * Double.parseDouble(arguments2[6]) * Double.parseDouble(arguments2[7]);
                            registBag(arguments2[1],arguments2[2], Double.parseDouble(arguments2[3]), arguments2[11], Double.parseDouble(arguments2[4])/5,
                             dimension, arguments2[8], Util.toDate(arguments2[9]), "n");
                        }
                        else{
                            double dimension = Double.parseDouble(arguments2[5]) * Double.parseDouble(arguments2[6]) * Double.parseDouble(arguments2[7]);
                            registBag(arguments2[1],arguments2[2], Double.parseDouble(arguments2[3]), arguments2[10], Double.parseDouble(arguments2[4])/5,
                             dimension, arguments2[8], Util.toDate(arguments2[9]), "y");
                        }
                        break;
                    case "Sneaker":
                        
                        if (arguments2[9].equals("No")){
                            registSneaker(arguments2[1],arguments2[2], Double.parseDouble(arguments2[3]), arguments2[10],  Double.parseDouble(arguments2[4])/5, 
                            Double.parseDouble(arguments2[5]), Util.toSneakerType(arguments2[6]), arguments2[7], Util.toDate(arguments2[8]), "n");
                        }
                        else{
                            registSneaker(arguments2[1],arguments2[2], Double.parseDouble(arguments2[3]), arguments2[10],  Double.parseDouble(arguments2[4])/5, 
                            Double.parseDouble(arguments2[5]), Util.toSneakerType(arguments2[6]), arguments2[7], Util.toDate(arguments2[8]), "y");
                        }
                        
                    break;
                    case "Tshirt":
                        
                        registTshirt(arguments2[1],arguments2[2], Double.parseDouble(arguments2[3]), arguments2[7], Double.parseDouble(arguments2[4])/5,
                        Util.toTshirtSize(arguments2[5]), Util.toTshirtPattern(arguments2[6]));
                       
                        break;
                    
                        
                    default:
                    throw new InvalidCommand(substrings[1],line);
                    
                }
            }catch(NullPointerException e){
                throw new InvalidCommand(substrings[1],line);
            }
            break;
            case "RegistarTransportadora":
            String[] arguments3 = substrings[2].split(";");
            try{
                if (arguments3[1].equals("No"))
                addCarrier(arguments3[0], Double.parseDouble(arguments3[2]), Double.parseDouble(arguments3[3]), Double.parseDouble(arguments3[4]), "n");
                else{
                    addCarrier(arguments3[0], Double.parseDouble(arguments3[2]), Double.parseDouble(arguments3[3]), Double.parseDouble(arguments3[4]), "y");
                }

            }catch(CarrierAlreadyExistsException e){

                throw new InvalidCommand(substrings[1],line);
            }
               
            break;
            case "FazerEncomenda":
            try{
                makeOrder(Util.toLinkedListParser(substrings[2]));  
            }catch(InvalidId e){
                throw new InvalidCommand(substrings[1],line);
            }
            break;
            case "AlterarTransportadora":
            String[] arguments4 = substrings[2].split(";");
            try{

                changeCarrier(arguments4[0], Double.parseDouble(arguments4[1]), Double.parseDouble(arguments4[2]), Double.parseDouble(arguments4[3]));

            }catch(NullPointerException e){

                throw new InvalidCommand(substrings[1],line);
            }
               
            break;
            case "TimeSkip":
               
            break;
        
            default:
            throw new InvalidCommand(substrings[1],line);
            
        }
    }
    catch(IllegalArgumentException e){
        throw new InvalidCommand("Unidentified",line);
    }
    catch(DateTimeParseException e){
        throw new InvalidCommand("Unidentified",line);
    }

    }

    public void Parser( String path) throws FileNotFoundException,IOException,InvalidCommand,IllegalArgumentException{
        // File path is passed as parameter
        File file = new File(path);
 
        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)
 
        // Creating an object of BufferedReader class
        BufferedReader br  = new BufferedReader(new FileReader(file));
 
        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        int line = 1;
        while ((st = br.readLine()) != null){
            if (Util.checkIgnore(st)){
             parserExecuter(st,line);
            }
            line++;
        }
        br.close();
        
    }
    

    
}
