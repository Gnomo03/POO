import java.util.TreeMap;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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


    public void save( ObjectOutputStream os ){
        try{
            os.writeObject( this.userMap );
        }
        catch( Exception ex){
        }
    }

    public void load( ObjectInputStream is ){
        TreeMap<Integer,User> temp = null;
        try{
            temp =  (TreeMap<Integer,User>) is.readObject();
            this.userMap = temp;
        }
        catch( Exception ex){
        }
    }
}