import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;



public class OrderManager {
    private HashMap<Integer, Order> orderMap; // deve ter de passar a treemap por questões de eficiencia
    

    public OrderManager() {
        this.orderMap = new HashMap<Integer, Order>();
    }

    public Order getOrder(int id) {
        if (this.orderMap.containsKey(id))
            return this.orderMap.get(id);
        return null;
    }

    /**
     * Returns a specific order
     *
     * @param id of the order
     * @return copy of the order
     */
    public Order searchOrder(int id) {
        return this.orderMap.get(id);
    }

    /**
     * Add´s a order to the order map
     *
     * @param order
     */
    public void addOrder(Order order) {
        this.orderMap.put(order.getID(), order.clone());
    }

    /**
     * Removes´s a order to the order map
     *
     * @param id of the order
     * @return the order;
     */
    public Order removeOrder(int id) {
        return this.orderMap.remove(id).clone();
    }
    /**
    public List<Item> updateOrders(LocalDate date) {
        List<Item> ret = new LinkedList<>();
        for (Integer key : this.orderMap.keySet()){

<<<<<<< Updated upstream
    public void updateOrders() {
        // to be defined
        
=======
            Order o  = this.orderMap.get(key);
            if (o.isPending() && o.getDate().isBefore(date)){
                List<Item> temp = o.setFinished();
                ret.addAll(temp);
                continue;
             }
             // definimos que o Dispatched demora 12 dias e é fixo
             LocalDate elevenDaysBeforeDate = date.minusDays(11);
             if (o.isDispatched() && o.getDate().isBefore(elevenDaysBeforeDate)){
                o.setFinished();
             }
    
    
>>>>>>> Stashed changes
    }
    return ret;
}
*/
    public List<Order> getOrders() {
        List<Order> orders = new LinkedList<Order>();
        for (Integer key : this.orderMap.keySet()) {
            Order value = this.orderMap.get(key);
            orders.add(value.clone());
        }
        return orders;
    }
}
