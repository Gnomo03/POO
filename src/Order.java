import java.time.LocalDate;
import java.util.List;
import java.util.LinkedList;

public class Order {
    private List<Item> itemList;
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
        this.itemList = new LinkedList<Item>();
        this.dimension = TypeOfSize.Little;
        this.finalPrice = 0;
        this.state = OrderState.Pending;
        this.date = LocalDate.now();
        this.id = currentID;
        currentID++;
    }

    public Order(List<Item> collection, TypeOfSize dimension, double finalPrice, OrderState state, LocalDate date){
        this.itemList = collection;
        this.dimension = dimension;
        this.finalPrice = finalPrice;
        this.state = state;
        this.date = date;
        this.id = currentID;
        currentID++;
    }

    public Order(Order oneOrder){
        this.itemList = oneOrder.getCollection();
        this.dimension = oneOrder.getDimension();
        this.finalPrice = oneOrder.getFinalPrice();
        this.state = oneOrder.getState();
        this.date = oneOrder.getDate();
        this.id = oneOrder.getID();
        currentID++;
    }

    public List<Item> getCollection(){
        return this.itemList;
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
        this.itemList = collection;
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
               "collection='" +this.itemList.toString()+ "\'\n" +
               "dimension='" +this.dimension+ "\'\n" +
               "Final Price='" +this.finalPrice+ "\'\n" +
               "State='" +this.state+ "\'\n" +
               "Date='" +this.date.toString()+ "\'\n" +
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
        int nmbr = this.itemList.size();
            if(nmbr ==1)
                this.dimension = TypeOfSize.Medium;
            if(nmbr ==5)
                this.dimension = TypeOfSize.Big;
        this.itemList.add(oneItem);
        this.finalPrice += oneItem.getPrice();
    }

    public void removeItem(Item oneItem){
        int nmbr = this.itemList.size();
            if(nmbr ==2)
                this.dimension = TypeOfSize.Little;
            if(nmbr ==6)
                this.dimension = TypeOfSize.Medium;
        this.itemList.remove(oneItem);
        this.finalPrice -= oneItem.getPrice();
    }

}