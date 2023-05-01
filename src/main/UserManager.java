import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

public class UserManager implements Serializable {
    private Map<Integer, User> userMap;
    

    public UserManager() {
        this.userMap = new HashMap<Integer, User>();
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

    private Map<Integer, User> getUserMap() {
        return this.userMap;
    }
    public Map<Integer, User> getUserMapCopy() {
        Map<Integer, User> userMapCopy = new HashMap<>();
    
        for (Map.Entry<Integer, User> entry : userMap.entrySet()) {
            int id = entry.getKey();
            User user = entry.getValue();
            userMapCopy.put(id, user.clone()); // add the copy to the new map
        }
    
        return userMapCopy;
    }
   public void deleteBills(Order order) {

        Map<Integer, User> it = this.getUserMap();
  
        for (int key : it.keySet()) {
  
          User u = it.get(key);
             for ( int keyBill: u.getBills().keySet()){
  
              Bill b = u.getBills().get(keyBill);
              if (b.getOrder().getID() == order.getID())
                  u.getBills().remove(keyBill);
  
             }
        }
  
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