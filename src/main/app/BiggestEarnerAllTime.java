package app;
import java.util.Map;

public class BiggestEarnerAllTime implements Querier{

    private Map<Integer,User> hm;

    public BiggestEarnerAllTime(Map<Integer,User> mapcopy){

        hm = mapcopy;
    }

    @Override
    public User execute() throws NullPointerException{ 

        if (hm.isEmpty())
            throw new NullPointerException("No user is in the Model");

        User biggestEarner = null;
        
        for (int user_key: hm.keySet()){

            User u = hm.get(user_key);
            if (biggestEarner == null || biggestEarner.soldItemsValue()< u.soldItemsValue()){
                    biggestEarner = u;
            }

        }
        return biggestEarner;
    }
    
}
