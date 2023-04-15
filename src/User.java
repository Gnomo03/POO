import java.util.ArrayList;
import java.util.List;

public class User implements Comparable<User>{
    
    private int id;
    private String email;
    private String name;
    private String address;
    private int nif;
    private double soldItemsValue;
    private String password;

    private List<Order> acquiredOrder;
    private List<Order> emittedOrder;
    private List<Item> sellingItems;

    private static int currentID = 0;

    public User() {

        this.id = currentID++;
        this.email = "n/d";
        this.name = "n/d";
        this.address = "n/d";
        this.nif = 0;
        this.soldItemsValue = 0.0;
        this.password = "n/d";
        this.acquiredOrder = new ArrayList<Order>();
        this.emittedOrder = new ArrayList<Order>();
        this.sellingItems = new ArrayList<Item>();


    }
    public User(String email, String name, String address,int nif,double soldItemsValue,String password,ArrayList<Order> acquiredOrder,ArrayList<Order> emittedOrder,ArrayList<Item> sellingItems) {

        this.id = currentID++;
        this.email = email;
        this.name = name;
        this.address = address;
        this.nif = nif;
        this.soldItemsValue = soldItemsValue;
        this.password = password;
        this.acquiredOrder =new ArrayList<>(acquiredOrder);
        this.emittedOrder = new ArrayList<>(emittedOrder);
        this.sellingItems = new ArrayList<>(sellingItems);

    }
    
    public User( User oneUser){

        this.id = oneUser.getId();
        this.email = oneUser.getEmail();
        this.name = oneUser.getName();
        this.address = oneUser.getAddress();
        this.nif = oneUser.getNif();
        this.soldItemsValue = oneUser.getSoldItemsValue();
        this.password = oneUser.getPassword();
        this.acquiredOrder = oneUser.getAcquiredOrder();
        this.emittedOrder = oneUser.getEmittedOrder();
        this.sellingItems = oneUser.getSellingItems();

    }
    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public int getNif() {
        return nif;
    }
    public double getSoldItemsValue() {
        return soldItemsValue;
    }
    public String getPassword() {
        return password;
    }
    public List<Order> getAcquiredOrder() {
        return new ArrayList<>(this.acquiredOrder);
    }
    public List<Order> getEmittedOrder() {
        return new ArrayList<>(this.emittedOrder);
    }
    public List<Item> getSellingItems() {

        return new ArrayList<>(this.sellingItems);
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setNif(int nif) {
        this.nif = nif;
    }
    public void setSoldItemsValue(double soldItemsValue) {
        this.soldItemsValue = soldItemsValue;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAcquiredOrder(List<Order> acquiredOrder) {
        this.acquiredOrder = new ArrayList<>(acquiredOrder);
    }
    public void setEmittedOrder(List<Order> emittedOrder) {
        this.emittedOrder = new ArrayList<>(emittedOrder);
    }
    public void setSellingItems(List<Item> sellingItems) {
        this.sellingItems = new ArrayList<>(sellingItems);
    }
    @Override
    public int compareTo(User otherUser) {
        return Double.compare(this.soldItemsValue, otherUser.soldItemsValue);
    }
    @Override
    public User clone() {
        return new User(this);
    }
    @Override
    public boolean equals(Object o) {

        if (o == null) return false;

        if (o.getClass() != this.getClass()) return false;

        User u = (User) o;
        
        return (u.getId() == this.getId()) && u.getEmail().equals(this.getEmail()) && u.getName().equals(this.getName()) 
                && u.getAddress().equals(this.getAddress()) && u.getNif() == this.getNif() && u.getSoldItemsValue() == this.getSoldItemsValue()
                && u.getPassword().equals(this.getPassword()) && u.getAcquiredOrder().equals(this.getAcquiredOrder()) && u.getEmittedOrder().equals(this.getEmittedOrder()) 
                && u.getSellingItems().equals(this.getSellingItems());

    }

    @Override
    public String toString() {
    return "User{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            ", nif=" + nif +
            ", soldItemsValue=" + soldItemsValue +
            ", password='" + password + '\'' +
            ", acquiredOrder=" + acquiredOrder +
            ", emittedOrder=" + emittedOrder +
            ", sellingItems=" + sellingItems +
            '}';
    }

    public void addItem(Item oneItem ) {
        this.sellingItems.add(oneItem);
    }
    public void addAcquireOrder(Order oneAcquireOrder) {
        this.acquiredOrder.add(oneAcquireOrder);
    }
    public void addEmittedOrder(Order oneEmittedOrder) {
        this.acquiredOrder.add(oneEmittedOrder);
    }
    public void removeItem(Item oneItem ) {
        this.sellingItems.remove(oneItem);
    }
    public void removeAcquireOrder(Order oneAcquireOrder) {
        this.acquiredOrder.remove(oneAcquireOrder);
    }
    public void removeEmittedOrder(Order oneEmittedOrder) {
        this.acquiredOrder.remove(oneEmittedOrder);
    }
    public boolean containsItem(Item oneItem ) {
        return this.sellingItems.contains(oneItem);
    }
    public boolean  containsAcquireOrder(Order oneAcquireOrder) {
        return this.acquiredOrder.contains(oneAcquireOrder);
    }
    public boolean containsEmittedOrder(Order oneEmittedOrder) {
       return this.acquiredOrder.contains(oneEmittedOrder);
    }
}
