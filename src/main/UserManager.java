import java.util.TreeMap;
import java.util.LinkedList;
import java.util.List;

public class UserManager {
    private TreeMap<Integer, User> userMap;
    private User currentUser;

    public UserManager() {
        this.userMap = new TreeMap<Integer, User>();
        User u = new User("admin", "admin", "admin", 0, "admin");
        this.addUser(u);
    }

    public User getUser(int id) {
        if (this.userMap.containsKey(id))
            return this.userMap.get(id).clone();
        return null;
    }

    public User getCurrentUser() {
        if (this.currentUser != null) {
            return this.currentUser.clone();
        } else {
            return null;
        }
    }

    public void setCurrentUser(int id) {
        this.currentUser = this.userMap.get(id);
    }

    /**
     * Adds an user to the map.
     *
     * @param oneUser to the user map
     */
    public void addUser(User oneUser) {
        this.userMap.put(oneUser.getId(), oneUser.clone());
    }

    /**
     * removes an user to the map.
     *
     * @param id of the user
     * @return the user that was removed
     */
    public User removeUser(int id) {
        return this.userMap.remove(id).clone();
    }

    /**
     * Returns every sales of an user
     *
     * @param id of the user
     * @return the sales of an user in a List
     */
    public List<Order> getUserSales(int id) {
        User u = this.userMap.get(id);
        List<Order> orders = new LinkedList<Order>();
        for (Order order : u.getEmittedOrder())
            orders.add(order.clone());
        return orders;
    }

    public List<User> getUsers() {
        List<User> users = new LinkedList<User>();
        for (Integer key : this.userMap.keySet()) {
            User value = this.userMap.get(key);
            users.add(value.clone());
        }
        return users;
    }

    public TreeMap<Integer, User> getUserMap() {
        return this.userMap;
    }

    public boolean reviewCredentials(String email) {
        boolean ret = true;
        for (Integer user_id : userMap.keySet()) {

            User u = userMap.get(user_id);
            if (u.getEmail().equals(email))
                ret = false;
        }
        return ret;
    }

    public User findUserByEmail(String email) {
        for (Integer user_id : userMap.keySet()) {

            User temp = this.userMap.get(user_id);
            if (temp.getEmail().equals(email))
                return temp.clone();
        }
        return null;
    }
}
