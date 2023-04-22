import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

public class Controller {

    private Module m;

    public Controller(Module m) {
        this.m = m;
    }

    public boolean login(String email, String password) {

        User u = m.findUserByEmail(email);
        if ( u == null ) {return false;}

        if (u.getPassword().equals(password)) {
            m.setCurrentUser(u.getId());
            return true;
        }
      
        return false;
    }

    public void logout() {

    }

    public void userRegistsItems(Item oneItem) {
      
    }

    public boolean registerUser(String email, String name, String address,int nif,String password){ 

        if (this.m.reviewCredentials(email)){
            User u = new User(email,name,address,nif,password);
            this.m.addUser(u);
            return true;
        }
        else {
            return false;
        }
    }

}
