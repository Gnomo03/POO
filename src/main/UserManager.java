import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class UserManager extends Module {

    /**
     * Searches for an item with the specified ID.
     *
     */
    public User getCurrentUser() {

        return this.getCurrentUser().clone();
    }

    public void setCurrentUser(int id) {

        User currentUser = this.getCurrentUser();
        currentUser = this.getUserMap().get(id);
    }

    /**
     * removes an user to the map.
     *
     * @param id of the user
     * @return the user that was removed
     */
    public User removeUser(int id) {

        return this.getUserMap().remove(id).clone();

    }

    /**
     * Returns every sales of an user
     *
     * @param id of the user
     * @return the sales of an user in a List
     */
    public List<Order> getUserSales(int id) {

        User u = this.getUserMap().get(id);
        List<Order> orders = new LinkedList<Order>();

        for (Order order : u.getEmittedOrder())
            orders.add(order.clone());

        return orders;
    }

    public User findUserByEmail(String email) {

        for (Integer user_id : this.getUserMap().keySet()) {

            User temp = this.getUserMap().get(user_id);
            if (temp.getEmail().equals(email))
                return temp.clone();
        }
        return null;
    }

    public boolean userRegistsItem(String email, Item item, String carrierName) {

        User u = this.findUserByEmail(email);
        if (u == null)
            return false;
        else {
            ItemManager itemManager = new ItemManager();
            item.setUserId(u.getId());
            item.setCarrier(this.getCarrierMap().get(carrierName));
            itemManager.addListedItem(item);
            Item i = itemManager.searchItem(item.getID());
            u.addItem(i);
            return true;
        }
    }

    public boolean reviewCredentials(String email) {

        boolean ret = true;
        for (Integer user_id : this.getUserMap().keySet()) {

            User u = this.getUserMap().get(user_id);
            if (u.getEmail().equals(email))
                ret = false;
        }
        return ret;
    }
}
