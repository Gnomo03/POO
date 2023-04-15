import java.time.LocalDate;
import java.util.List;

public class Order {
    private List<Item> collection;
    private TypeOfSize dimension;
    private double finalPrice;
    private OrderState state;
    private LocalDate date;
    private int id;

    private static int currentID = 0;

    public enum TypeOfSize{
        Little, Medium, Big;
    }

    public enum OrderState {
        Pending, Finished, Dispatched;
    }
    
    public Order(){
        this.collection = null;
        this.dimension = null;
        this.finalPrice = 0;
        this.state = OrderState.Pending;
        this.date = LocalDate.now();
        this.id = currentID;
        currentID++;
    }

    public Order(List<Item> collection, TypeOfSize dimension, double finalPrice, OrderState state, LocalDate date){
        this.collection = collection;
        this.dimension = dimension;
        this.finalPrice = finalPrice;
        this.state = state;
        this.date = date;
        this.id = currentID;
        currentID++;
    }

    public Order(Order oneOrder){
        this.collection = oneOrder.getCollection();
        this.dimension = oneOrder.getDimension();
        this.finalPrice = oneOrder.getFinalPrice();
        this.state = oneOrder.getState();
        this.date = oneOrder.getDate();
        this.id = oneOrder.getID();
        currentID++;
    }

    public List<Item> getCollection(){
        return this.collection;
    }

    public TypeOfSize getDimension(){
        return this.dimension;
    }

    public double getFinalPrice(){
        return this.finalPrice;
    }

    public OrderState getState(){
        return this.state;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public int getID(){
        return this.id;
    }

    public void setCollection(List<Item> collection){
        this.collection = collection;
    }

    public void setDimension(TypeOfSize dimension){
        this.dimension = dimension;
    }

    public void setFinalPrice(double finalPrice){
        this.finalPrice = finalPrice;
    }

    public void setState(OrderState state){
        this.state = state;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public String toString(){
        return "Order{" +
               "collection='" +this.collection.toString()+ "\'" +
               "dimension='" +this.dimension+
               "Final Price='" +this.finalPrice+
               "State='" +this.state+
               "Date='" +this.date.toString()+ "\'" +
               "ID='" +this.id+ "}";
    }
    
    public boolean equals(Object o){
        if (o == this)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;
        Order e = (Order) o;
        return this.getCollection().equals(e.getCollection()) &&
               this.getDimension().equals(e.getDimension()) &&
               this.getFinalPrice() == e.getFinalPrice() &&
               this.getState().equals(e.getState()) &&
               this.getID() == e.getID();
    }

    public Order clone(){
        return new Order(this);
    }

    public void addItem(Item oneItem){
        this.collection.add(oneItem);
        this.finalPrice += oneItem.getPrice();
    }

    public void removeItem(Item oneItem){
        this.collection.remove(oneItem);
        this.finalPrice -= oneItem.getPrice();
    }

}