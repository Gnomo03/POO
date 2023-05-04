package app;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PodiumSeller implements Querier{

    private Map<Integer,User> hm;
    private LocalDate date1;
    private LocalDate date2;
    public PodiumSeller(Map<Integer,User> mapcopy,LocalDate date1,LocalDate date2){

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
    public Object execute() throws NullPointerException {
        if (hm.isEmpty()) {
            throw new NullPointerException("No user is in the Model");
        }
    
        List<User> topSellers = new ArrayList<>(hm.values());
    
        for (int i = 0; i < topSellers.size() - 1; i++) {
            for (int j = i + 1; j < topSellers.size(); j++) {
                User user1 = topSellers.get(i);
                User user2 = topSellers.get(j);
                if (user2.soldItemsValueFrame(date1, date2) > user1.soldItemsValueFrame(date1, date2)) {
                    topSellers.set(i, user2);
                    topSellers.set(j, user1);
                }
            }
        }
        topSellers.removeIf(user -> user.getEmail().equals("admin"));
        return topSellers;
    }
    
    
}
