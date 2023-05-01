import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Controller {

    private Model m;

    public Controller(Model m) {
        this.m = m;
    }

    public User getCurrentUser() throws NullPointerException, UserIsAdminException {
        return this.m.getCurrentUser();
    }

    public void login(String email, String password) throws NullPointerException, MissedIdException {

        User u = m.lookupUser(email);

        if (u.getPassword().equals(password)) {
            m.setCurrentUser(u.getId());
        } else {
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
            String material, LocalDate releaseDate) throws UserIsAdminException {

        m.registBag(description, brand, basePrice,
                carrier, conditionScore, dimension, material, releaseDate,
                this.m.getCurrentUser().getId());
    }

    public void registItemTshirt(String description, String brand, double basePrice,
            String carrier, double conditionScore,
            Tshirt.TshirtSize size, Tshirt.TshirtPattern pattern) throws UserIsAdminException, NullPointerException {
        m.registTshirt(description, brand, basePrice, carrier,
                conditionScore, size, pattern,
                this.m.getCurrentUser().getId());
    }

    public void registItemSneaker(String description, String brand, double basePrice,
            String carrier, double conditionScore,
            double size, Sneaker.SneakerType type, String color, LocalDate releaseDate)
            throws UserIsAdminException, NullPointerException {
        m.registSneaker(description, brand, basePrice,
                carrier, conditionScore, size, type, color, releaseDate,
                this.m.getCurrentUser().getId());
    }

    public void registerUser(String email, String name, String address, int nif, String password)
            throws UserAlreadyExistsException {

        User u = new User(email, name, address, nif, password);
        this.m.registsUser(u);

    }

    public void advanceTime(LocalDate date) {

        m.TimeSkip(date);
    }

    public void placeOrder(List<Integer> order) throws UserIsAdminException {

        m.makeOrder(m.getCurrentUser().getId(), order);

    }

    public String displayCarriers() {
        String result = "\n";
        for (Carrier c : m.getCarrierManagerList()) {
            result += c.toString() + "\n";
        }
        return result;
    }

    public String displayListedItems() throws UserIsAdminException {
        List<Item> items = m.getListedItemsManagerList();
        List<Item> ret = new LinkedList<Item>();
        for (Item item : items) {

            if (item.getUserId() != m.getCurrentUser().getId())
                ret.add(item);
        }
        return ret.toString();
    }

    public String getCurrentUserListedItems() throws UserIsAdminException {

        return m.getCurrentUser().getSellingItems().toString();
    }

    public String getCurrentUserSystemItems() throws UserIsAdminException {

        return m.getCurrentUser().getSystemItems().toString();
    }

    public void listSystemItem(int item_id) throws UserIsAdminException {

        m.alterItemState(item_id, m.getCurrentUser().getId());

    }

    public List<Order> getCurrentUserAllOrders() throws UserIsAdminException {

        return m.checkThisUserOrders(m.getCurrentUser().getId());

    }

    public LocalDate accessDate() {
        return m.getDate();
    }

    public void returnOrderId(int orderId) throws UserIsAdminException, OrderNotReturnable {

        m.deleteOrder(orderId, m.getCurrentUser().getId());

    }

    public void save() throws FileNotFoundException, IOException {
        this.m.save("data.ser");
    }

    public void load() throws FileNotFoundException, IOException, ClassNotFoundException {
        this.m = Model.load("data.ser");
    }

    @Override
    public String toString() {
        return m.toString();
    }

    public void registCarrier(String name, double taxSmall, double taxMedium, double taxBig)
            throws CarrierAlreadyExistsException {

        m.addCarrier(name, taxSmall, taxMedium, taxBig);

    }

    public void changeCarrier(String name, double taxSmall, double taxMedium, double taxBig)
            throws NullPointerException {
        m.changeCarrier(name, taxSmall, taxMedium, taxBig);
    }

    public String querrierExecution(int query, LocalDate date1, LocalDate date2, int id) throws NullPointerException {
        String result = "";
        Querier querier;
        switch (query) {
            case (1):
                querier = new BiggestEarnerAllTime(m.getUserManagerCopy());
                User u = (User) querier.execute();
                result = u.toString();
                break;
            case (3):
                querier = new BiggestCarrier(m.getCarrierManagerCopy());
                Carrier c = (Carrier) querier.execute();
                result = c.toString();
                break;
            case (4):
                querier = new EmmitedOrderList(m.getUserManagerCopy(), id);
                List<Order> o = (List<Order>) querier.execute();
                result = o.toString();
                break;
            case (5):
                querier = new VintageProfit(m);
                result = querier.execute().toString();
                break;
        }
        return result;
    }

}