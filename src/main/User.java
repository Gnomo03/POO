package main;

import java.util.ArrayList;
import java.util.List;

/**
 * The User class represents a user of an online marketplace. It stores information
 * such as the user's ID, email, name, address, NIF, password, as well as lists of orders
 * and items associated with the user.
 */
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
     /**
     * Constructs a new user with default values for all fields.
     */
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
     /**
     * Constructs a new user with the specified values for all fields.
     *
     * @param email         the user's email address
     * @param name          the user's name
     * @param address       the user's address
     * @param nif           the user's tax identification number
     * @param soldItemsValue the total value of items sold by the user
     * @param password      the user's password
     * @param acquiredOrder the orders acquired by the user
     * @param emittedOrder  the orders emitted by the user
     * @param sellingItems  the items being sold by the user
     */
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
    /**
     * Constructs a new user with the specified values for a few fields
     *
     * @param email         the user's email address
     * @param name          the user's name
     * @param address       the user's address
     * @param nif           the user's tax identification number
     * @param password      the user's password
     */
    public User(String email, String name, String address,int nif,String password) {

        this.id = currentID++;
        this.email = email;
        this.name = name;
        this.address = address;
        this.nif = nif;
        this.soldItemsValue = 0.0;
        this.password = password;
        this.acquiredOrder =new ArrayList<>();
        this.emittedOrder = new ArrayList<>();
        this.sellingItems = new ArrayList<>();

    }
    /**
     * Constructs a new user that is a copy of the specified user.
     *
     * @param oneUser the user to copy
     */
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
     /**
     * Gets the ID of this user.
     *
     * @return the user's ID
     */
    public int getId() {
        return id;
    }
    /**
     * Gets the email address of this user.
     *
     * @return the user's email address
     */
    public String getEmail() {
        return email;
    }
    /**
     * Gets the name of this user.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the user's address.
     *
     * @return the user's address
     */
    public String getAddress() {
        return address;
    }
    /**
     * Returns the user's NIF (tax identification number).
     *
     * @return the user's NIF
     */
    public int getNif() {
        return nif;
    }
    /**
     * Returns the user's total sold item's value.
     *
     * @return the user's soldItemsValue
     */
    public double getSoldItemsValue() {
        return soldItemsValue;
    }
    /**
     * Returns the user's password.
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }
    /**
     * Returns the user's acquiered orders.
     *
     * @return the user's acquiered orders
     */
    public List<Order> getAcquiredOrder() {
        return new ArrayList<>(this.acquiredOrder);
    }
    /**
     * Returns the user's emitted orders.
     *
     * @return the user's emitted orders
     */
    public List<Order> getEmittedOrder() {
        return new ArrayList<>(this.emittedOrder);
    }
    /**
     * Returns the user's items.
     *
     * @return the user's items
     */
    public List<Item> getSellingItems() {

        return new ArrayList<>(this.sellingItems);
    }
    /**
     * @param email
     * Set the user's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @param name
     * Set the user's name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param address
     * Set the user's address.
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * @param nif
     * Set the user's nif.
     */
    public void setNif(int nif) {
        this.nif = nif;
    }
    /**
     * @param soldItemsValue
     * Set the user's total sold items value.
     */
    public void setSoldItemsValue(double soldItemsValue) {
        this.soldItemsValue = soldItemsValue;
    }
     /**
     * @param password
     * Set the user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @param acquiredOrder
     * Set the user's acquired orders.
     */
    public void setAcquiredOrder(List<Order> acquiredOrder) {
        this.acquiredOrder = new ArrayList<>(acquiredOrder);
    }
    /**
     * @param emittedOrder
     * Set the user's emitted orders.
     */
    public void setEmittedOrder(List<Order> emittedOrder) {
        this.emittedOrder = new ArrayList<>(emittedOrder);
    }
    /**
     * @param sellingItems
     * Set the user's selling items.
     */
    public void setSellingItems(List<Item> sellingItems) {
        this.sellingItems = new ArrayList<>(sellingItems);
    }
    /**
     * Compares user´s by the total amount of sales.
     * 
     * @param otherUser
     * @return result of comparation
     */
    @Override
    public int compareTo(User otherUser) {
        return Double.compare(this.soldItemsValue, otherUser.soldItemsValue);
    }
    /**
     * Clone a user
     * 
     * @return new user object
     */
    @Override
    public User clone() {
        return new User(this);
    }
    /**
     * See´s if two users are equal
     * @param o
     * @return return result of comparation
     */
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
    /**
     * Makes a string out of a user
     * 
     * @return String conversion of object user
    */
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
    /**
    * @param oneItem
    * Add an item to the user
    */
    public void addItem(Item oneItem ) {
        this.sellingItems.add(oneItem);
    }
    /**
    * @param oneAcquiredOrder
    * Adds an acquired order to the user
    */
    public void addAcquireOrder(Order oneAcquireOrder) {
        this.acquiredOrder.add(oneAcquireOrder);
    }
    /**
    * @param oneEmittedOrder
    * Add an emitted order to the user
    */
    public void addEmittedOrder(Order oneEmittedOrder) {
        this.acquiredOrder.add(oneEmittedOrder);
    }
    /**
    * @param oneItem
    * Removes an item order to the user
    */
    public void removeItem(Item oneItem ) {
        this.sellingItems.remove(oneItem);
    }
    /**
    * @param oneAcquiredOrder
    * Removes an acquired order to the user
    */
    public void removeAcquireOrder(Order oneAcquireOrder) {
        this.acquiredOrder.remove(oneAcquireOrder);
    }
    /**
    * @param oneEmittedOrder
    * Removes an emitted order to the user
    */
    public void removeEmittedOrder(Order oneEmittedOrder) {
        this.acquiredOrder.remove(oneEmittedOrder);
    }
    /**
    * Check´s if user has the item
    * @param oneItem
    * @return returns true if it has the item
    */
    public boolean containsItem(Item oneItem ) {
        return this.sellingItems.contains(oneItem);
    }
    /**
    * Check´s if user has the acquired order
    * @return returns true if it has the order
    */
    public boolean  containsAcquireOrder(Order oneAcquireOrder) {
        return this.acquiredOrder.contains(oneAcquireOrder);
    }
    /**
    * Check´s if user has the emitted order
    * @return returns true if it has the order
    */
    public boolean containsEmittedOrder(Order oneEmittedOrder) {
       return this.acquiredOrder.contains(oneEmittedOrder);
    }
    public Item searchItem(int item_id) {
        for(Item i : this.sellingItems){
            if (i.getID() == item_id)
                return i;
        }
        return null;
    }
    public void itemUpdate(int item_id){

        Item i = searchItem(item_id);
            if (i!= null) {

                this.soldItemsValue += i.getPrice()*0.92;
                removeItem(i);
            }

    }
}
