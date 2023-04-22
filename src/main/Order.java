import java.time.LocalDate;
import java.util.List;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class Order {
    private List<Item> collection;
    private HashMap<String,Integer> carrierHelper;
    private TypeOfSize dimension;
    private double itemPrice;
    private double satisfactionPrice;
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
        this.collection = new LinkedList<Item>();
        this.carrierHelper = new HashMap<String,Integer>();
        this.dimension = TypeOfSize.Little;
        this.itemPrice = 0;
        this.satisfactionPrice = 0;
        this.state = OrderState.Pending;
        this.date = LocalDate.now();
        this.id = currentID;
        currentID++;
    }

    public Order(List<Item> collection,HashMap<String,Integer> carrierHelper,TypeOfSize dimension,double satisfactionPrice,double itemPrice, OrderState state, LocalDate date){
        this.collection = collection;
        this.dimension = dimension;
        this.carrierHelper = carrierHelper;
        this.itemPrice = itemPrice;
        this.satisfactionPrice = satisfactionPrice;
        this.state = state;
        this.date = date;
        this.id = currentID;
        currentID++;
    }

    public Order(Order oneOrder){
        this.collection = oneOrder.getCollection();
        this.dimension = oneOrder.getDimension();
        this.itemPrice = oneOrder.getItemPrice();
        this.carrierHelper = oneOrder.getCarrierHelper();
        this.satisfactionPrice = oneOrder.getSatisfactionPrice();
        this.state = oneOrder.getState();
        this.date = oneOrder.getDate();
        this.id = oneOrder.getID();
    }
    public double getSatisfactionPrice(){
        return this.satisfactionPrice;
    }
    public HashMap<String,Integer> getCarrierHelper(){
        return this.carrierHelper;
    }

    public List<Item> getCollection(){
        return this.collection;
    }

    public TypeOfSize getDimension(){
        return this.dimension;
    }

    public double getItemPrice(){
        return this.itemPrice;
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

    public void setItemPrice(double itemPrice){
        this.itemPrice = itemPrice;
    }
    public void setSatisfactionPrice(double Price){
        this.satisfactionPrice = Price;
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
               "Final Price='" +this.itemPrice+
               "Satisfaction Price='" +this.satisfactionPrice+
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
               this.getItemPrice() == e.getItemPrice() &&
               this.getSatisfactionPrice() == e.getSatisfactionPrice() &&
               this.getState().equals(e.getState()) &&
               this.getID() == e.getID();
    }

    public Order clone(){
        return new Order(this);
    }

    public void addItem(Item oneItem){
        int nmbr = this.collection.size();
            if(nmbr ==1)
                this.dimension = TypeOfSize.Medium;
            if(nmbr ==5)
                this.dimension = TypeOfSize.Big;
        this.collection.add(oneItem);
        this.itemPrice += oneItem.getPrice(); // Adicionar valor do pedido

        if(this.carrierHelper.containsKey(oneItem.getCarrier().getName()))
        this.carrierHelper.put(oneItem.getCarrier().getName(), this.carrierHelper.get(oneItem.getCarrier().getName()) + 1);
        else 
        this.carrierHelper.put(oneItem.getCarrier().getName(), 1);
    
        if(oneItem.getConditionScore()==1) 
            this.satisfactionPrice+=0.5;
        else {
            this.satisfactionPrice+=0.25;
        }
    }

    public void removeItem(Item oneItem){
        int nmbr = this.collection.size();
            if(nmbr ==2)
                this.dimension = TypeOfSize.Little;
            if(nmbr ==6)
                this.dimension = TypeOfSize.Medium;
        this.collection.remove(oneItem);
        this.itemPrice -= oneItem.getPrice();

        this.carrierHelper.put(oneItem.getCarrier().getName(), this.carrierHelper.get(oneItem.getCarrier().getName()) - 1);
    }

    public double calculateFinalPrice(){
        double tax = 0;
        for(Item i : this.collection){

            int many = this.carrierHelper.get(i.getCarrier().getName());

                if (many == 1)
                    tax += i.getPrice()*i.getCarrier().getTaxSmall();
                if (many >= 2 && many <= 5)
                    tax += i.getPrice()*i.getCarrier().getTaxMedium();
                if (many >5)
                    tax += i.getPrice()*i.getCarrier().getTaxBig();

        }
        
        return this.itemPrice+this.satisfactionPrice+tax;
    }

    public static Comparator<Order> priceComparator = new Comparator<Order>() {
        public int compare(Order e1, Order e2){
            return Double.compare(e1.getItemPrice(), e2.getItemPrice());
        }
    };

}
