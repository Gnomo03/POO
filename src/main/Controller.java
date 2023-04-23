import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

public class Controller {

    private Module m;

    public Controller(Module m) {
        this.m = m;
    }

    public boolean login(String email, String password) {

        User u = m.findUserByEmail(email);
        if ( u == null ) {return false;}

        if (u.getPassword().equals(password)) {
            m.setCurrentUser(u.getId());
            return true;
        }
      
        return false;
    }

    public void logout() {
        m.setCurrentUser(-1);
    }

    /*
    public void userRegistsItems(Item oneItem) {
      
    }
 */

    public boolean registItemBag(String description, String brand, String reference, double basePrice, double priceCorrection,
    Carrier carrier, double conditionScore, int previousOwners, boolean premiumStat,int userId, double dimension, String material, int date){
        Item b = new Bag(description, brand, reference, basePrice, priceCorrection, carrier, conditionScore, previousOwners, 
                        premiumStat, dimension, material, date, userId);
        this.m.addListedItem(b);
        return true;   
    }

    public boolean registItemTshirt(String description, String brand, String reference, double basePrice, double priceCorrection,
    Carrier carrier, double conditionScore, int previousOwners, boolean premiumStat,int userId, Tshirt.TshirtSize size, Tshirt.TshirtPattern pattern){
        Item t = new Tshirt(description, brand, reference, basePrice, priceCorrection, carrier, conditionScore, previousOwners,
                            premiumStat, size, pattern, userId);
        this.m.addListedItem(t);
        return true;
    }

    public boolean registItemSneaker(String description, String brand, String reference, double basePrice, double priceCorrection,
    Carrier carrier, double conditionScore, int previousOwners, boolean premiumStat,int userId, double size, Sneaker.SneakerType type,
    String color, int date){
        Item s = new Sneaker(description, brand, reference, basePrice, priceCorrection, carrier, conditionScore, previousOwners,
                    premiumStat, size, type, color, date, userId);
        this.m.addListedItem(s);
        return true;
    }

    public boolean registerUser(String email, String name, String address,int nif,String password){ 

        if (this.m.reviewCredentials(email)){
            User u = new User(email,name,address,nif,password);
            this.m.addUser(u);
            return true;
        }
        else {
            return false;
        }
    }

}
