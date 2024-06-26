import java.util.TreeMap;
import java.util.LinkedList;
import java.util.List;

public class UserManager {
    private TreeMap<Integer, User> userMap;

    public UserManager() {
        this.userMap = new TreeMap<Integer, User>();
        User u = new User("admin", "admin", "admin", 0, "admin");
        this.addUser(u);
    }

    public User getUser(int id) {
        if (this.userMap.containsKey(id))
            return this.userMap.get(id);
        return null;
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

    public User findUserByEmail(String email) {
        for (Integer user_id : userMap.keySet()) {

            User temp = this.userMap.get(user_id);
            if (temp.getEmail().equals(email))
                return temp;
        }
        return null;
    }

    public String serialize() {
        String result = "";
        for (User u : userMap.values()) {
            result += u.serialize(Consts.DELIM_1) + "\n";
        }
        return result;
    }

    public String deserialize(List<String> Lines) {
        String result = "";

        this.userMap.clear();
        for (String line : Lines) {
            User u = new User();
            u.deserialize(Consts.DELIM_1, line);
            this.addUser(u);
        }
        return result;
    }

}