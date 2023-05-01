import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface Querier {
    public User biggestEarnerAllTime(Map<Integer,User> hm);
    public User biggestEarnerTimeFrame(Map<Integer,User> hm,LocalDate date1, LocalDate date2);
    public Carrier biggestCarrier(Map<Integer,Carrier> hm);
    public List<Order> emittedOrderList(Map<Integer,User> hm, int userId);
    public List<User> podiumSeller(Map<Integer,User> hm,LocalDate date1, LocalDate date2);
    public List<User> podiumSpenders(Map<Integer,User> hm,LocalDate date1, LocalDate date2);
    public double vintageProfit();

}

