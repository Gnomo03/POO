import java.util.TreeMap;
import java.util.LinkedList;
import java.util.List;
import java.io.Serializable;

public class UserManager implements Serializable {
    private TreeMap<Integer, User> userMap;
    

    public UserManager() {
        this.userMap = new TreeMap<Integer, User>();
    }

    public User getUser(int id) throws NullPointerException {
        if (!this.userMap.containsKey(id)){
            throw new NullPointerException();
        }
    

        return this.userMap.get(id);
    }

    /**
     * Adds an user to the map.
     *
     * @param oneUser to the user map
     */
    public void addUser(User oneUser) throws NullPointerException{
        if (oneUser == null){
            new NullPointerException();
        }
        this.userMap.put(oneUser.getId(), oneUser.clone());
    }

    /**
     * removes an user to the map.
     *
     * @param id of the user
     * @return the user that was removed
     */
    public User removeUser(int id) {
        return this.userMap.remove(id);
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

    

}