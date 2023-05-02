import java.time.LocalDate;
import java.util.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;



//import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class Order implements Serializable,Comparable<Order> {
    private List<Item> collection;
    private HashMap<String, Integer> carrierHelper;
    private List<User> sellers;
    private User buyer;
    private TypeOfSize dimension;
    private double itemPrice;
    private double satisfactionPrice;
    private OrderState state;
    private LocalDate date;
    private int id;
    private double endPrice;

    private static int currentID = 1;

    public enum TypeOfSize {
        Little, Medium, Big;
    }

    public enum OrderState {
        Pending, Finished, Dispatched;
    }

    public Order() {
        this.collection = new LinkedList<Item>();
        this.carrierHelper = new HashMap<String, Integer>();
        this.dimension = TypeOfSize.Little;
        this.itemPrice = 0;
        this.satisfactionPrice = 0;
        this.state = OrderState.Pending;
        this.date = LocalDate.now();
        this.id = currentID++;
        this.endPrice = 0;
        this.buyer = null;
        this.sellers = new LinkedList<User>();
        
    }

    public Order(List<Item> collection, HashMap<String, Integer> carrierHelper, TypeOfSize dimension,
            double satisfactionPrice, double itemPrice, OrderState state, LocalDate date,double endPrice,User buyer,List<User> sellers) {
        this.collection = collection;
        this.dimension = dimension;
        this.carrierHelper = carrierHelper;
        this.itemPrice = itemPrice;
        this.satisfactionPrice = satisfactionPrice;
        this.state = state;
        this.date = date;
        this.id = currentID++;
        this.endPrice = endPrice;
        this.buyer = buyer;
        this.sellers = sellers;
    }

    public Order(Order oneOrder) {
        this.collection = oneOrder.getCollection();
        this.dimension = oneOrder.getDimension();
        this.itemPrice = oneOrder.getItemPrice();
        this.carrierHelper = oneOrder.getCarrierHelper();
        this.satisfactionPrice = oneOrder.getSatisfactionPrice();
        this.state = oneOrder.getState();
        this.date = oneOrder.getDate();
        this.id = oneOrder.getID();
        this.endPrice = oneOrder.getEndPrice();
        this.buyer = oneOrder.getBuyer();
        this.sellers = oneOrder.getSellers();
    }
    public double getEndPrice(){
        return this.endPrice;
    }
    public double getSatisfactionPrice() {
        return this.satisfactionPrice;
    }
    public User getBuyer(){
        return this.buyer;
    }
    public List <User> getSellers() {
        return this.sellers;
    }


    public HashMap<String, Integer> getCarrierHelper() {
        return this.carrierHelper;
    }
    public void setDispatched(){
        this.state = OrderState.Dispatched;
        
    }
    public List<Item>  setFinished(){
        // gerar faturas
        this.state = OrderState.Finished;
        return this.collection;
    }

    public List<Item> getCollection() {
        return this.collection;
    }

    public TypeOfSize getDimension() {
        return this.dimension;
    }
    public boolean isPending(){
        return this.state.equals(OrderState.Pending);
    }
    public boolean isFinished(){
        return this.state.equals(OrderState.Finished);
    }
    public boolean isDispatched(){
        return this.state.equals(OrderState.Dispatched);
    }

    public double getItemPrice() {
        return this.itemPrice;
    }

    public OrderState getState() {
        return this.state;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public int getID() {
        return this.id;
    }
    public void setEndPrice(double endPrice){
        this.endPrice = endPrice;
    }
    public void setCollection(List<Item> collection) {
        this.collection = collection;
    }

    public void setDimension(TypeOfSize dimension) {
        this.dimension = dimension;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setSatisfactionPrice(double Price) {
        this.satisfactionPrice = Price;
    }

    public void setState(OrderState state) {
        if (state == OrderState.Finished){
            this.endPrice = calculateFinalPrice();
        }

        this.state = state;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
    public void setSeller(LinkedList<User> sellers) {
      this.sellers = sellers;
    }
    public List<Item> itemPerUser(User user) {

        List<Item> list = new LinkedList<Item>();

        for(Item i : this.collection){
            
            if (i.getonePreviousOwners() == user.getId())
                list.add(i);
        }
        
        return list;
    }
    public String toString() {
        return "Order{" +
                "collection='" + this.collection.toString() + "\'" +
                " dimension='" + this.dimension +
                " Final Price='" + this.itemPrice +
                " Satisfaction Price='" + this.satisfactionPrice +
                " State='" + this.state +
                " Date='" + this.date.toString() + "\'" +
                " Iva= 13%'" + "\'" +
                "ID='" + this.id + "}";
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || o.getClass() != this.getClass())
            return false;
        Order e = (Order) o;
        return this.getCollection().equals(e.getCollection()) &&
                this.getDimension().equals(e.getDimension()) &&
                this.getItemPrice() == e.getItemPrice() &&
                this.getSatisfactionPrice() == e.getSatisfactionPrice() &&
                this.getState().equals(e.getState()) &&
                this.getBuyer().equals(e.getBuyer()) &&
                this.getSellers().equals(e.getSellers()) &&
                this.getID() == e.getID();
    }

    public Order clone() {
        return new Order(this);
    }

    public void addItem(Item oneItem,User owner) {
        int nmbr = this.collection.size();
        if (nmbr == 1)
            this.dimension = TypeOfSize.Medium;
        if (nmbr == 5)
            this.dimension = TypeOfSize.Big;
        this.collection.add(oneItem);
        this.sellers.add(owner);
        
        this.itemPrice += oneItem.getPrice(); // Adicionar valor do pedido

        if (this.carrierHelper.containsKey(oneItem.getCarrier().getName()))
            this.carrierHelper.put(oneItem.getCarrier().getName(),
                    this.carrierHelper.get(oneItem.getCarrier().getName()) + 1);
        else
            this.carrierHelper.put(oneItem.getCarrier().getName(), 1);

        if (oneItem.getConditionScore() == 1)
            this.satisfactionPrice += 0.5;
        else {
            this.satisfactionPrice += 0.25;
        }
    }

    public void removeItem(Item oneItem,User owner) {
        int nmbr = this.collection.size();
        if (nmbr == 2)
            this.dimension = TypeOfSize.Little;
        if (nmbr == 6)
            this.dimension = TypeOfSize.Medium;
        this.collection.remove(oneItem);
        this.itemPrice -= oneItem.getPrice();

        this.carrierHelper.put(oneItem.getCarrier().getName(),
                this.carrierHelper.get(oneItem.getCarrier().getName()) - 1);
    }

    public double calculateFinalPrice() {
        double tax = 0;
        for (Item i : this.collection) {

            int many = this.carrierHelper.get(i.getCarrier().getName());

            if (many == 1)
                tax += i.getPrice() * i.getCarrier().getTaxSmallWithIva();
            if (many >= 2 && many <= 5)
                tax += i.getPrice() * i.getCarrier().getTaxMediumWithIva();
            if (many > 5)
                tax += i.getPrice() * i.getCarrier().getTaxBigWithIva();

        }
        return this.itemPrice + this.satisfactionPrice + tax;
    }

    @Override
    public int compareTo(Order o) {
        LocalDate date1 = this.getDate();
        LocalDate date2 = o.getDate();
        return date1.compareTo(date2);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // default serialization
        out.writeInt(currentID); // save static variable
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // default deserialization
        currentID = in.readInt(); // load static variable
    }

    public double getItemPricePerCarrier(String carrier_name) {
        
        double total = 0;
        for (Item i : this.collection){

            if (i.getCarrier().getName().equals(carrier_name)){
                total += i.getPrice();
            }

        }
        return total;
    }

}