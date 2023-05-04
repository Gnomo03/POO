package app;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private Model m;

    public Controller(Model m) {
        this.m = m;
    }

    public User getCurrentUser() throws NullPointerException,UserIsAdminException {
        return this.m.getCurrentUser();
    }

    public void login(String email, String password) throws NullPointerException,MissedIdException{

        User u = m.lookupUser(email);
        

        if (u.getPassword().equals(password)) {
            m.setCurrentUser(u.getId());
        }else{
            throw new MissedIdException();
        }

    }

    public void logout() {
        m.setCurrentUser(-1);
    }

    /*
     * public void userRegistsItems(Item oneItem) {
     * 
     * }
     */

    public void registItemBag(String description, String brand, double basePrice,
            String carrier, double conditionScore, double dimension,
            String material, LocalDate releaseDate, String premium) throws UserIsAdminException {

                m.registBag(description, brand, basePrice,
                carrier, conditionScore, dimension, material, releaseDate,
                this.m.getCurrentUser().getId(),premium);
    }

    public void registItemTshirt(String description, String brand, double basePrice,
            String carrier, double conditionScore,
            Tshirt.TshirtSize size, Tshirt.TshirtPattern pattern) throws UserIsAdminException,NullPointerException  {
                m.registTshirt(description, brand, basePrice, carrier,
                conditionScore, size, pattern,
                this.m.getCurrentUser().getId());
    }

    public void registItemSneaker(String description, String brand, double basePrice,
            String carrier, double conditionScore,
            double size, Sneaker.SneakerType type, String color, LocalDate releaseDate, String premium)throws UserIsAdminException,NullPointerException {
                m.registSneaker(description, brand, basePrice,
                carrier, conditionScore, size, type, color, releaseDate,
                this.m.getCurrentUser().getId(),premium);
    }

    public void registerUser(String email, String name, String address, int nif, String password) throws UserAlreadyExistsException {
            
            
            User u = new User(email, name, address, nif, password);
            this.m.registsUser(u);
            
        
    }
    public void advanceTime(LocalDate date) {

        m.TimeSkip(date);
    }
    public void placeOrder(List<Integer> order) throws UserIsAdminException  {

        m.makeOrder(m.getCurrentUser().getId(), order);

    }
    private String displayNormalCarriers(){
        String result = "\n";
        for (Carrier c : m.getCarrierManagerList()) {
            if (c instanceof Premium){
                
            }else{
                result += c.toString() + "\n";
            }

            
        }
        return result;

    }
    private String displayPremiumCarriers(){
        String result = "\n";
        for (Carrier c : m.getCarrierManagerList()) {
            if (c instanceof Premium){
                PremiumCarrier c1 = (PremiumCarrier) c;
                result += c1.toString() + "\n";
            }
                
        }
        return result;

    }
    public String displayCarriers(String premium) {
        if (premium.equals("y"))
        return displayPremiumCarriers();
        else
        return displayNormalCarriers();
    }
    public String displayAllCarriers() {
        String result = "\n";
        for (Carrier c : m.getCarrierManagerList()) {
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

    public String displayListedItems() throws UserIsAdminException  {
        List <Item> items = m.getListedItemsManagerList();
        List <Item> ret = new LinkedList<Item>();
        for (Item item : items) {

            if (item.getUserId() != m.getCurrentUser().getId())
                    ret.add(item);                
        }
        return ret.toString();
    }

    public String getCurrentUserListedItems()throws UserIsAdminException {

        return m.getCurrentUser().getSellingItems().toString();
    }
    public String getCurrentUserSystemItems() throws UserIsAdminException {

        return m.getCurrentUser().getSystemItems().toString();
    }

    public void listSystemItem(int item_id) throws UserIsAdminException { 

        m.alterItemState(item_id,m.getCurrentUser().getId());

    }
    public List<Order> getCurrentUserAllOrders() throws UserIsAdminException {

       return m.checkThisUserOrders(m.getCurrentUser().getId());

    }
    public LocalDate accessDate(){
        return m.getDate();
    }
    public void returnOrderId(int orderId)throws UserIsAdminException,OrderNotReturnable {

        m.deleteOrder(orderId,m.getCurrentUser().getId());

    }
    public void save() throws FileNotFoundException,IOException{
        this.m.save("data.ser");
    }

    public void load()throws FileNotFoundException,IOException,ClassNotFoundException{
        this.m = Model.load("data.ser");
    }

    @Override
    public String toString() {
        return m.toString();
    }

    public void registCarrier(String name, double taxSmall, double taxMedium, double taxBig,String premium) throws CarrierAlreadyExistsException{

        m.addCarrier(name, taxSmall, taxMedium, taxBig,premium);

    }

    public void changeCarrier(String name, double taxSmall, double taxMedium, double taxBig) throws NullPointerException{
        m.changeCarrier(name, taxSmall, taxMedium, taxBig);
    }
    @SuppressWarnings("unchecked")
    public String querrierExecution(int query,LocalDate date1, LocalDate date2,int userID) throws NullPointerException{
        String result = "";
        Querier querier;
        switch(query){
            case(1):
            querier = new BiggestEarnerAllTime(m.getUserManagerCopy());
            User u = (User) querier.execute();
            result = u.toString();
            break;
            case(2):
            querier = new BiggestEarnerAllTimeFrame(m.getUserManagerCopy(),date1,date2);
            User u2 = (User) querier.execute();
            result = u2.toString();
            break;
            case(3):
            querier = new BiggestCarrier(m.getCarrierManagerCopy());
            Carrier c = (Carrier) querier.execute();
            result = c.toString();
            break;
            case(4):
            querier = new EmmitedOrderList(m.getUserManagerCopy(),userID);
            List<Order> l = (LinkedList<Order>) querier.execute();
            result = l.toString();
            break;
            case(5):
            querier = new PodiumSeller(m.getUserManagerCopy(),date1,date2);
            List<User> l1 = (ArrayList<User>) querier.execute();
            result = l1.toString();
            break;
            case(6):
            querier = new PodiumSpenders(m.getUserManagerCopy(),date1,date2);
            List<User> l2 = (ArrayList<User>) querier.execute();
            result = l2.toString();
            break;
            case(7):
            querier = new VintageProfit(m.getVintageProfit());
            double d = (double) querier.execute();
            result = String.format("%f", d);
            break;
        }
        return result;
    }
    
}