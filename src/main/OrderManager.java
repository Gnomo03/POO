import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class OrderManager extends Module {

    /**
     * Returns a specific order
     *
     * @param id of the order
     * @return copy of the order
     */
    public Order searchOrder(int id) {
        return this.getOrderMap().get(id).clone();
    }

    /**
     * Add´s a order to the order map
     *
     * @param order
     */
    public void addOrder(Order order) {
        this.getOrderMap().put(order.getID(), order.clone());
    }

    /**
     * Removes´s a order to the order map
     *
     * @param id of the order
     * @return the order;
     */
    public Order removeOrder(int id) {
        return this.getOrderMap().remove(id).clone();
    }

    public void updateOrders() {

        // to be defined
        double vintageProfit = this.getVintageProfit();
        vintageProfit = 0;
    }

    public void makeOrder(int id_user, List<Integer> items_keys) {
        Order order = new Order();
        ItemManager itemManager = new ItemManager();
        for (Integer current_key : items_keys) {
            Item i = this.getListedItemsMap().get(current_key);
            order.addItem(i);
            itemManager.updateItem(i.getID());
            User u = this.getUserMap().get(i.getUserId());
            u.itemUpdate(current_key);
        }
        this.addOrder(order);
    }
}
