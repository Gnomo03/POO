package app;

import java.util.Map;

public class BiggestCarrier implements Querier {

    private Map<String, Carrier> hm;

    public BiggestCarrier(Map<String, Carrier> mapcopy) {

        hm = mapcopy;
    }

    @Override
    public Carrier execute() throws NullPointerException {

        if (hm.isEmpty())
            throw new NullPointerException("No Carrier is in the Model");

        Carrier biggestEarner = null;

        for (String key : hm.keySet()) {

            Carrier c = hm.get(key);
            if (biggestEarner == null || biggestEarner.getTotalEarning() < c.getTotalEarning()) {
                biggestEarner = c;
            }

        }
        return biggestEarner;
    }

}