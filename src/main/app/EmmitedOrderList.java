package app;

import java.util.Map;
import java.util.List;

public class EmmitedOrderList implements Querier {

    private Map<Integer, User> hm;
    private int id;

    public EmmitedOrderList(Map<Integer, User> mapcopy, int userIdcopy) {
        hm = mapcopy;
        id = userIdcopy;
    }

    @Override
    public List<Order> execute() throws NullPointerException {

        if (hm.isEmpty())
            throw new NullPointerException("No user is in the Model");

        User u = hm.get(id);
        if (u == null)
            throw new NullPointerException("User not in the system");

        return u.getEmmitedOrder();
    }
}