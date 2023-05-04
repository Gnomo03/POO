package app;
import java.time.LocalDate;
import java.util.Map;

public class BiggestEarnerAllTimeFrame implements Querier{

    private Map<Integer,User> hm;
    private LocalDate date1;
    private LocalDate date2;

    public BiggestEarnerAllTimeFrame(Map<Integer,User> mapcopy,LocalDate date1,LocalDate date2){

        hm = mapcopy;

        if (date1.isBefore(date2)){
            this.date1=date1;
            this.date2=date2;
        }
        else{
            this.date1=date2;
            this.date2=date1;
        }
            
    }

    @Override
    public User execute() throws NullPointerException{ 

        if (hm.isEmpty())
            throw new NullPointerException("No user is in the Model");

        User biggestEarner = null;
        
        for (int user_key: hm.keySet()){

            User u = hm.get(user_key);
            if (biggestEarner == null || biggestEarner.soldItemsValueFrame(date1,date2)< u.soldItemsValueFrame(date1,date2)){
                    biggestEarner = u;
            }

        }
        if (biggestEarner.getEmail().equals("admin")){
            throw new NullPointerException("No user sold items in the time frame");
        }
        return biggestEarner;
    }
    
}