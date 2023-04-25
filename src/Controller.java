import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Controller {

    private static String USER_DATA_FILE = "user.data";
    private static String ITEM_DATA_FILE = "item.data";

    private Module m;

    public Controller(Module m) {
        this.m = m;
    }

    public User getCurrentUser(){
        return this.m.getCurrentUser();
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

    public Carrier getCarrier(String id){
        Carrier res = this.m.searchCarrier(id);
        return res;
    }


    public boolean registItemBag(String description, String brand, String reference, double basePrice, double priceCorrection,
                                 Carrier carrier, double conditionScore, int previousOwners, boolean premiumStat,
                                 int userId, double dimension, String material, int date){
        Item b = new Bag(description, brand, reference, basePrice, priceCorrection, carrier, conditionScore, previousOwners, 
                        premiumStat, dimension, material, date, userId);
        this.m.addListedItem(b);
        return true;   
    }

    public boolean registItemTshirt(String description, String brand, String reference, double basePrice, double priceCorrection,
                                    Carrier carrier, double conditionScore, int previousOwners, boolean premiumStat,int userId, 
                                    Tshirt.TshirtSize size, Tshirt.TshirtPattern pattern){
        Item t = new Tshirt(description, brand, reference, basePrice, priceCorrection, carrier, conditionScore, previousOwners,
                            premiumStat, size, pattern, userId);
        this.m.addListedItem(t);
        return true;
    }

    public boolean registItemSneaker(String description, String brand, String reference, double basePrice, double priceCorrection,
                                     Carrier carrier, double conditionScore, int previousOwners, boolean premiumStat,int userId, 
                                     double size, Sneaker.SneakerType type,
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

    
    public boolean saveData(){        
        boolean result = false;
        try{
            //Users
            FileWriter fw = new FileWriter(USER_DATA_FILE);
            fw.write( this.m.SerializeUsers() );
            //fw.write(m.toJson());
            fw.close();
            
            //Items
            fw = new FileWriter(ITEM_DATA_FILE);
            fw.write( this.m.SerializeItems() );
            fw.close();

            result = true;
        }
        catch( Exception ex ){
            // Avisar o utilizador

        }
        
        return result;
    }

    public boolean loadData(){ 
        boolean result = false;
        try{
            //Users
            File f = new File(USER_DATA_FILE);
            if( f.exists() ){
                List<String>  lines = Files.readAllLines( Path.of(USER_DATA_FILE ) );
                m.DeSerializeUsers(lines);
            }
            //
            f = new File(ITEM_DATA_FILE);
            if( f.exists() ){
                List<String> lines = Files.readAllLines( Path.of(ITEM_DATA_FILE ) );
                m.DeSerializeItems(lines);
            }

            // Others...
            result = true;
        }
        catch( Exception ex){
        }
        return result;
    }
}
