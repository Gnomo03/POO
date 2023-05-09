package app;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Bill implements Serializable {

    enum TypeBill {
        BOUGHT,
        SOLD
    }

    private final int billNumber;
    private TypeBill type;
    private Map<Integer, Item> items;
    private double totalCost;
    private double portsTax;
    private Order order;

    private static int bill_count = 1;

    public Bill() {

        this.billNumber = bill_count++;
        this.type = null;
        this.items = new HashMap<>();
        this.totalCost = 0;
        this.order = null;
    }

    public Bill(TypeBill type, Map<Integer, Item> article, double totalCost, Order o) {
        this.billNumber = bill_count++;
        this.type = type;
        this.items = new HashMap<Integer, Item>();
        for (Map.Entry<Integer, Item> e : article.entrySet()) {
            this.items.put(e.getKey(), e.getValue());
        }
        this.totalCost = totalCost;
        this.order = o;
    }

    public Bill(Bill f) {
        this.billNumber = f.getbillNumber();
        this.type = f.gettype();
        this.items = f.getitems();
        this.totalCost = f.gettotalCost();
        this.portsTax = f.getportsTax();
        this.order = f.getOrder();
    }

    /**
     * Getters
     */
    public int getbillNumber() {
        return this.billNumber;
    }

    public TypeBill gettype() {
        return this.type;
    }

    public Order getOrder() {
        return this.order;
    }

    public double getportsTax() {
        if (this.type.equals(TypeBill.SOLD))
            return 0.0;
        else
            return this.portsTax;
    }

    public Map<Integer, Item> getitems() {
        Map<Integer, Item> map = new HashMap<Integer, Item>();
        for (Map.Entry<Integer, Item> e : this.items.entrySet()) {
            map.put(e.getKey(), e.getValue());
        }
        return map;
    }

    public double gettotalCost() {
        return this.totalCost;
    }

    /**
     * Setters.
     */
    public void setitems(Map<Integer, Item> art) {
        Map<Integer, Item> map = new HashMap<Integer, Item>();
        for (Map.Entry<Integer, Item> e : art.entrySet()) {
            map.put(e.getKey(), e.getValue());
        }
        this.items = map;
    }

    public void settotalCost(double valor) {
        this.totalCost = valor;
    }

    public void setSold() {
        this.type = TypeBill.SOLD;
    }

    public void setBought() {
        this.type = TypeBill.BOUGHT;
    }

    public void setOrder(Order oneOrder) {
        this.order = oneOrder;
    }

    /**
     * Método clone.
     */
    @Override
    public Bill clone() {
        return new Bill(this);
    }

    /**
     * Método toString
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bill Number: ").append(billNumber).append("\n");
        sb.append("Type: ").append(type).append("\n");
        sb.append("Items:\n");
        for (Map.Entry<Integer, Item> entry : items.entrySet()) {
            sb.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        sb.append("Total Cost: ").append(totalCost).append("\n");
        sb.append("Ports Tax: ").append(portsTax).append("\n");
        sb.append("Order: ").append(order.getID()).append("\n");
        sb.append("Amount: ").append(getAmount()).append("\n");
        return sb.toString();
    }

    public String showBill() {

        String ret = "";
        for (Integer i_key : this.items.keySet()) {
            Item i = this.items.get(i_key);
            ret += i.showItem();
        }
        ret = ret + '\n' + " ID= " + this.billNumber + ", " + " Total Cost= " + this.totalCost + ", " + " Ports Tax= "
                + this.portsTax
                + ", " + " Order= "
                + this.order.getID()
                + "Type= " + this.type
                + "Amount= " + getAmount();

        return ret;

    }

    /**
     * Método equals.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (this.getClass() != obj.getClass()))
            return false;
        Bill f = (Bill) obj;
        return this.getbillNumber() == f.getbillNumber() &&
                this.gettype().equals(f.gettype()) &&
                this.getitems().equals(f.getitems()) &&
                this.gettotalCost() == f.gettotalCost();
    }

    public void addItem(Item item, int many_tax) {
        this.items.put(item.getID(), item);
        Carrier c = item.getCarrier();
        double tax = 0;
        if (many_tax == 1)
            tax = c.getTaxSmallWithIva();
        if (many_tax >= 2 && many_tax <= 5)
            tax = c.getTaxMediumWithIva();
        if (many_tax > 5)
            tax = c.getTaxBigWithIva();

        this.portsTax += tax * item.getBasePrice();
        calculateTotalCostItems();
    }

    public void removeItem(Item item, int many_tax) {
        this.items.remove(item.getID());
        Carrier c = item.getCarrier();
        double tax = 0;
        if (many_tax == 1)
            tax = c.getTaxSmallWithIva();
        if (many_tax >= 2 && many_tax <= 5)
            tax = c.getTaxMediumWithIva();
        if (many_tax > 5)
            tax = c.getTaxBigWithIva();

        this.portsTax -= tax * item.getBasePrice();
        if (many_tax == 2)
            this.portsTax = (this.portsTax * c.getTaxSmallWithIva()) / c.getTaxMediumWithIva();
        if (many_tax == 6)
            this.portsTax = (this.portsTax * c.getTaxMediumWithIva()) / c.getTaxBigWithIva();
        calculateTotalCostItems();
    }

    public void calculateTotalCostItems() {
        double sum = 0;
        for (Integer key : this.items.keySet()) {

            Item i = this.items.get(key);
            sum += i.getPrice();

        }
        this.totalCost = sum;
    }

    public double getAmount() {

        if (this.type.equals(TypeBill.SOLD))
            return this.totalCost * 0.988;
        else
            return this.portsTax + this.totalCost;

    }

    public boolean isSold() {
        return this.type.equals(TypeBill.SOLD);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // default serialization
        out.writeInt(bill_count); // save static variable
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // default deserialization
        bill_count = in.readInt(); // load static variable
    }

}